package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.impls.AuthenticationModelImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.impls.FoodDeliveryModelImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.LoginPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.LoginView
import com.padcmyanmar.padcx.fooddeliveryassignment.network.remoteConfig.FirebaseRemoteConfigManager
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {
    var mAuthModel = AuthenticationModelImpl
    var mFoodDeliveryModel = FoodDeliveryModelImpl

    override fun onTapLogin(email: String, password: String) {
        mAuthModel.login(email, password, {
            mView?.navigateToHomeScreen()
        }, {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapSignup() {
        mView?.navigateToRegisterScreen()
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mFoodDeliveryModel.setupRemoteConfigWithDefaultValues()
        mFoodDeliveryModel.fetchRemoteConfig()
    }
}