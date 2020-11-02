package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters

import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.CheckoutViewItemActionDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.CheckoutView
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/2/2020.
 */
interface CheckoutPresenter : BasePresenter<CheckoutView>, CheckoutViewItemActionDelegate  {
    fun onTapCheckout(orderList: List<FoodVO>)
}