package com.padcmyanmar.padcx.fooddeliveryassignment.network

import android.graphics.Bitmap
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
interface FirebaseApi {
    fun uploadImageAndUpdateProfile(image: Bitmap, onSuccess: (String) -> Unit)

    /*home Screen*/
    fun getCategories(onSuccess: (categoryList: List<CategoryVO>) -> Unit, onFailure:(String) -> Unit)

    fun getRestaurants(onSuccess: (restaurantList: List<RestaurantVO>) -> Unit, onFailure:(String) -> Unit)

    fun getPopularChoices(onSuccess: (popularList: List<FoodVO>) -> Unit, onFailure:(String) -> Unit)

    /*checkout Screen*/
    fun addFoodItem(food: FoodVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun deleteFoodItem(id: String)

    fun getOrderList(onSuccess: (orderList: List<FoodVO>) -> Unit, onFailure: (String) -> Unit)

    /*details screen*/
    fun getFoodItems( documentId: String, onSuccess: (foodList: List<FoodVO>) -> Unit, onFailure: (String) -> Unit)

    fun getRestaurantData(documentId: String, onSuccess: (restaurant: RestaurantVO) -> Unit, onFailure:(String) -> Unit)

}