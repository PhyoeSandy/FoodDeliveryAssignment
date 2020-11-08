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
            imageUrl?.let { url ->
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
                    onFailure(it.message ?: NO_INTERNET)
                } ?: kotlin.run {
                    val categoryList: MutableList<CategoryVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
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
                    onFailure(it.message ?: NO_INTERNET)
                } ?: kotlin.run {
                    val restaurantList: MutableList<RestaurantVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val data = document.data
                        var restaurant = RestaurantVO()
                        restaurant.name = data?.get("name") as String
                        restaurant.image = data["image"] as String?
                        restaurant.id = document.id
                        restaurant.ratings = data["ratings"] as Double
                        restaurant.type = data["type"] as String?
                        restaurant.address = data["address"] as String?

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
/*
        db.collectionGroup("foodItems").whereEqualTo("popular", "true")
*/
        db.collectionGroup("foodItems")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: NO_INTERNET)
                } ?: run {

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

    override fun addFoodItem(food: FoodVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        db.collection("orders")
            .document(food?.name.toString())
            .set(food)
            .addOnSuccessListener {
                onSuccess()
                Log.d("Success", "Successfully added foodItem")
            }
            .addOnFailureListener {
                onFailure("Order fail")
                Log.d("Failure", "Failed to add foodItem")
            }
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
                } ?: run {

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
        onSuccess: (foodList: List<FoodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection("restaurant/${documentId}/foodItems")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val burgerVOList: MutableList<FoodVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<FoodVO>(Data, FoodVO::class.java)
                        burgerVOList.add(docsData)
                    }
                    onSuccess(burgerVOList)
                }
            }
    }

    override fun getRestaurantData(
        documentId: String,
        onSuccess: (restaurant: RestaurantVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("restaurant").document(documentId)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run{

                    val data = value?.data
                    val restaurant = RestaurantVO()
                    restaurant.image = data?.get("image") as String
                    restaurant.name = data["name"] as String?
                    restaurant.ratings = data["ratings"] as Double
                    restaurant.type = data["type"] as String?
                    restaurant.address = data["address"] as String?

                    onSuccess(restaurant)
                }
            }
    }

}