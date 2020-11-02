package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views

import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/2/2020.
 */
interface CheckoutView : BaseView {
    fun showOrderList(orderList: List<FoodVO>)
}