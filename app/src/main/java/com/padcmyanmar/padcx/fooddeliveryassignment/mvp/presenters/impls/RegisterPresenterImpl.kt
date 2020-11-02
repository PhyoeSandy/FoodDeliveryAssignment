package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.impls.AuthenticationModelImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.RegisterPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.RegisterView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {
    private val mAuthModel = AuthenticationModelImpl

    override fun onTapLogin() {
       mView?.navigateToLoginScreen()
    }

    override fun onTapCreateAccount(
        userName: String,
        email: String,
        password: String,
        phone: String
    ) {
        mAuthModel.register(userName, email, password, phone, {
            mView?.navigateToLoginScreen()
        },{
            mView?.showErrorMessage(it)
        })
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
    }
}