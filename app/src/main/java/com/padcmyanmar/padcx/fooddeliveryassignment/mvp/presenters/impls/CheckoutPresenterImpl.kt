package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.impls.FoodDeliveryModelImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.CheckoutPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.CheckoutView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/2/2020.
 */
class CheckoutPresenterImpl : CheckoutPresenter, AbstractBasePresenter<CheckoutView>() {
    private val mFoodDeliveryModel = FoodDeliveryModelImpl

    override fun onTapCheckout(orderList: List<FoodVO>) {
        for(order in orderList) {
            mFoodDeliveryModel.removeFoodItem(order.name.toString())
        }
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mFoodDeliveryModel.getOrderList({
            mView?.showOrderList(it)
        }, {
            mView?.showErrorMessage(it)
        })
    }
}