package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.FoodItemDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.RestaurantDetailView
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

interface RestaurantDetailPresenter : BasePresenter<RestaurantDetailView>, FoodItemDelegate {
    fun onUiReady(lifecycleOwner: LifecycleOwner, documentId: String)
    fun onTapGoToCart()
}