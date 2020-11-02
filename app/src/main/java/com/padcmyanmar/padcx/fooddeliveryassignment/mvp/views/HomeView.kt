package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views

import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
interface HomeView : BaseView {
    fun showCategories(categoryList: List<CategoryVO>)
    fun showRestaurants(restaurantList: List<RestaurantVO>)
    fun showPopularChoices(popularList: List<FoodVO>)
    fun navigateToRestaurantDetailsScreen(documentId: String)
    fun changeHomeScreenViewType(viewType: Int)
}