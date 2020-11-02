package com.padcmyanmar.padcx.fooddeliveryassignment.network

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.fooddeliveryassignment.utils.NO_INTERNET
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
object CloudFirestoreApiImpl : FirebaseApi {
    private val storage = FirebaseStorage.getInstance().reference
    private val db = Firebase.firestore

    override fun uploadImageAndUpdateProfile(image: Bitmap, onSuccess: (String) -> Unit) {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storage.child(
            "images/${UUID.randomUUID()}"
        )

        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnSuccessListener {
            Log.d("Image Upload: ", "Success")
        }.addOnFailureListener {
            Log.d("Image Upload: ", it.toString())
        }

        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener {
            val imageUrl = it.result?.toString()
            imageUrl?.let{ url ->
                onSuccess(url)
            }
        }
    }

    override fun getCategories(
        onSuccess: (categoryList: List<CategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("category")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message?: NO_INTERNET)
                } ?: kotlin.run {
                    val categoryList : MutableList<CategoryVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for(document in result) {
                        val data = document.data
                        var category = CategoryVO()
                        category.name = data?.get("name") as String
                        category.image = data["image"] as String?

                        categoryList.add(category)
                    }
                    onSuccess(categoryList)
                }
            }
    }

    override fun getRestaurants(
        onSuccess: (restaurantList: List<RestaurantVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("restaurant")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message?: NO_INTERNET)
                } ?: kotlin.run {
                    val restaurantList : MutableList<RestaurantVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for(document in result) {
                        val data = document.data
                        var restaurant = RestaurantVO()
                        restaurant.name = data?.get("name") as String
                        restaurant.image = data["image"] as String?

                        restaurantList.add(restaurant)
                    }
                    onSuccess(restaurantList)
                }
            }
    }

    override fun getPopularChoices(
        onSuccess: (popularList: List<FoodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collectionGroup("foodItems").whereEqualTo("popular","true")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: NO_INTERNET)
                } ?: run{

                    val popularList: MutableList<FoodVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<FoodVO>(Data, FoodVO::class.java)
                        popularList.add(docsData)
                    }

                    onSuccess(popularList)
                }
            }
    }

    override fun addFoodItem(food: FoodVO) {
        db.collection("orders")
            .document(food?.name.toString())
            .set(food)
            .addOnSuccessListener { Log.d("Success", "Successfully added foodItem") }
            .addOnFailureListener { Log.d("Failure", "Failed to add foodItem") }
    }

    override fun deleteFoodItem(id: String) {
        db.collection("orders")
            .document(id)
            .delete()
            .addOnSuccessListener { Log.d("Success", "Successfully deleted foodItem") }
            .addOnFailureListener { Log.d("Failure", "Failed to delete foodItem") }
    }

    override fun getOrderList(
        onSuccess: (orderList: List<FoodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("orders")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run{

                    val orderList: MutableList<FoodVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<FoodVO>(Data, FoodVO::class.java)
                        orderList.add(docsData)
                    }

                    onSuccess(orderList)
                }
            }
    }

    override fun getFoodItems(
        documentId: String,
        onSuccess: (foodList: List<FoodVO>, restaurantVO: RestaurantVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        var restaurant: RestaurantVO  = RestaurantVO()
        db.collection("restaurant").document(documentId)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: NO_INTERNET)
                } ?: run{

                    val data = value?.data
                    val restaurantVO = RestaurantVO()
                    restaurantVO.name = data?.get("name") as String
                    restaurantVO.address = data["address"] as String?
                    restaurantVO.image = data["image"] as String?
                    restaurantVO.ratings = data["ratings"] as Double?

                    restaurant = restaurantVO
                }
            }

        db.collection("restaurants/${documentId}/foodItems")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: NO_INTERNET)
                } ?: run{

                    val foodList: MutableList<FoodVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<FoodVO>(Data, FoodVO::class.java)
                        foodList.add(docsData)
                    }
                    onSuccess(foodList ,restaurant)
                }
            }
    }
}