package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.IntroPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.IntroView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

class IntroPresenterImpl : IntroPresenter, AbstractBasePresenter<IntroView>() {
    override fun onTapCreateAccount() {
        mView?.navigateToRegisterScreen()
    }

    override fun onTapLogin() {
        mView?.navigateToLoginScreen()
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {}

}