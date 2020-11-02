package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.FoodDeliveryModel
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.impls.FoodDeliveryModelImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.HomePresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.HomeView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
class HomePresenterImpl : AbstractBasePresenter<HomeView>(), HomePresenter {
    var mFoodDeliveryModel: FoodDeliveryModel = FoodDeliveryModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mView?.changeHomeScreenViewType(
            mFoodDeliveryModel.getHomeScreenViewTypeFromRemote()
        )

        mFoodDeliveryModel.getCategoryList({
            mView?.showCategories(it)
        }, {
            mView?.showErrorMessage(it)
        })

        mFoodDeliveryModel.getRestaurantList({
            mView?.showRestaurants(it)
        }, {
            mView?.showErrorMessage(it)
        })

        /*mFoodDeliveryModel.getPopularItemList({
            mView?.showPopularChoices(it)
        }, {
            mView?.showErrorMessage(it)
        })*/

    }

    override fun onTapRestaurantItem(documentId: String) {
        mView?.navigateToRestaurantDetailsScreen(documentId)
    }

    override fun onTapFoodItem(food: FoodVO) {

    }
}