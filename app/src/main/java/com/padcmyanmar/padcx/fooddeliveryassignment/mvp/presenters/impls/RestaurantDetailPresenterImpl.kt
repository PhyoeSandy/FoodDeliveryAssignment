package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.impls.FoodDeliveryModelImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.RestaurantDetailPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.RestaurantDetailView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/2/2020.
 */
class RestaurantDetailPresenterImpl : AbstractBasePresenter<RestaurantDetailView>(),
    RestaurantDetailPresenter {
    private val mFoodDeliveryModel = FoodDeliveryModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner, documentId: String) {
        mFoodDeliveryModel.getFoodItems(documentId, { foodList, restaurant ->
            mView?.showPopularChoicesFoodItem(
                foodList.filter { it.popular }
            )

            mView?.showFoodItemList(foodList)

            mView?.showRestaurantData(restaurant)
        }, {
            mView?.showErrorMessage(it)
        })
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        TODO("Not yet implemented")
    }

    override fun onTapGoToCart() {
        TODO("Not yet implemented")
    }

    override fun onTapFoodItem(foodItem: FoodVO) {
        TODO("Not yet implemented")
    }
}