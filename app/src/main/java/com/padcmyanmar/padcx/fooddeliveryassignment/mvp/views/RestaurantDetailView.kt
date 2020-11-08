package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views

import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

interface RestaurantDetailView : BaseView {
    fun showRestaurantData(restaurantVO: RestaurantVO)
    fun showPopularChoicesFoodItem(popularFoodList: List<FoodVO>)
    fun showFoodItemList(foodList: List<FoodVO>)
    fun showAddToCartButton(cartCount: Long)
}