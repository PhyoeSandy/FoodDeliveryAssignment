package com.padcmyanmar.padcx.fooddeliveryassignment.data.model

import android.graphics.Bitmap
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.fooddeliveryassignment.network.FirebaseApi
import com.padcmyanmar.padcx.fooddeliveryassignment.network.remoteConfig.FirebaseRemoteConfigManager

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
interface FoodDeliveryModel {
    var mFirebaseApi: FirebaseApi
    var mFirebaseRemoteConfig: FirebaseRemoteConfigManager

    /*profile Screen*/
    fun updateProfile(bitmap: Bitmap)

    /*home Screen*/
    fun setupRemoteConfigWithDefaultValues()

    fun fetchRemoteConfig()

    fun getHomeScreenViewTypeFromRemote(): Int

    fun getCategoryList(
        onSuccess: (categoryList: List<CategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getRestaurantList(
        onSuccess: (restaurantList: List<RestaurantVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPopularItemList(onSuccess: (foodList: List<FoodVO>) -> Unit, onFailure: (String) -> Unit)

    /*checkout Screen*/
    fun addFoodItem(food: FoodVO)

    fun removeFoodItem(id: String)

    fun getOrderList(onSuccess: (orderList: List<FoodVO>) -> Unit, onFailure: (String) -> Unit)

    /*details Screen*/
    fun getFoodItems(documentId: String, onSuccess: (List<FoodVO>,RestaurantVO) -> Unit, onFaiure: (String) -> Unit)
}