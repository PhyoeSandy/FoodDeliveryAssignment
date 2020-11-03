package com.padcmyanmar.padcx.fooddeliveryassignment.data.model.impls

import android.graphics.Bitmap
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.FoodDeliveryModel
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.fooddeliveryassignment.network.CloudFirestoreApiImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.network.FirebaseApi
import com.padcmyanmar.padcx.fooddeliveryassignment.network.RealtimeDatabaseApiImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.network.remoteConfig.FirebaseRemoteConfigManager

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
object FoodDeliveryModelImpl : FoodDeliveryModel {

    //override var mFirebaseApi: FirebaseApi = RealtimeDatabaseApiImpl
    override var mFirebaseApi: FirebaseApi = CloudFirestoreApiImpl
    override var mFirebaseRemoteConfig: FirebaseRemoteConfigManager = FirebaseRemoteConfigManager

    override fun updateProfile(bitmap: Bitmap) {
        mFirebaseApi.uploadImageAndUpdateProfile(bitmap, {

        })
    }

    override fun setupRemoteConfigWithDefaultValues() {
        mFirebaseRemoteConfig.setUpRemoteConfigWithDefaultValues()
    }

    override fun fetchRemoteConfig() {
        mFirebaseRemoteConfig.fetchRemoteConfigs()
    }

    override fun getHomeScreenViewTypeFromRemote(): Int {
        return mFirebaseRemoteConfig.getHomeScreenViewTypeStatus()
    }

    override fun getCategoryList(
        onSuccess: (categoryList: List<CategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getCategories(onSuccess, onFailure)
    }

    override fun getRestaurantList(
        onSuccess: (restaurantList: List<RestaurantVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getRestaurants(onSuccess, onFailure)
    }

    override fun getPopularItemList(
        onSuccess: (foodList: List<FoodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPopularChoices(onSuccess, onFailure)
    }

    override fun addFoodItem(food: FoodVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.addFoodItem(food, onSuccess, onFailure)
    }

    override fun removeFoodItem(id: String) {
        mFirebaseApi.deleteFoodItem(id)
    }

    override fun getOrderList(
        onSuccess: (orderList: List<FoodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getOrderList(onSuccess, onFailure)
    }

    override fun getFoodItems(
        documentId: String,
        onSuccess: (List<FoodVO>) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.getFoodItems(documentId, onSuccess, onFaiure)
    }

    override fun getRestaurantData(
        documentId: String,
        onSuccess: (restaurant: RestaurantVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getRestaurantData(documentId, onSuccess, onFailure)
    }


}