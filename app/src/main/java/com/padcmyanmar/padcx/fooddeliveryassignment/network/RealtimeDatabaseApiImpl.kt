package com.padcmyanmar.padcx.fooddeliveryassignment.network

import android.graphics.Bitmap
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
object RealtimeDatabaseApiImpl : FirebaseApi {

    private val mDatabase : DatabaseReference = Firebase.database.reference
    private val storage = FirebaseStorage.getInstance()
    private val storageReferance = storage.reference

    override fun uploadImageAndUpdateProfile(image: Bitmap, onSuccess: (String) -> Unit) {

    }

    override fun getCategories(
        onSuccess: (categoryList: List<CategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mDatabase.child("category").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val restaurantTypeList = arrayListOf<CategoryVO>()
                snapshot.children.forEach { dataSnapshot ->
                    dataSnapshot.getValue(CategoryVO::class.java)?.let {
                        restaurantTypeList.add(it)
                    }
                }
                onSuccess(restaurantTypeList)
            }
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }
        })
    }

    override fun getRestaurants(
        onSuccess: (restaurantList: List<RestaurantVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mDatabase.child("restaurant").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val restaurantList = arrayListOf<RestaurantVO>()
                snapshot.children.forEach { dataSnapshot ->
                    dataSnapshot.getValue(RestaurantVO::class.java)?.let {
                        restaurantList.add(it)
                    }
                }
                onSuccess(restaurantList)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

        })
    }

    override fun getPopularChoices(
        onSuccess: (popularList: List<FoodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
    }

    override fun addFoodItem(food: FoodVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        //mDatabase.child("ordered_food_list").child(id.toString()).child(name).setValue(OrderedFoodListVO(name,price,quantity))
    }

    override fun deleteFoodItem(id: String) {
        mDatabase.child("ordered_food_list").child(id.toString()).removeValue()
    }

    override fun getOrderList(
        onSuccess: (orderList: List<FoodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getFoodItems(
        documentId: String,
        onSuccess: (foodList: List<FoodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getRestaurantData(
        documentId: String,
        onSuccess: (restaurant: RestaurantVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }
}