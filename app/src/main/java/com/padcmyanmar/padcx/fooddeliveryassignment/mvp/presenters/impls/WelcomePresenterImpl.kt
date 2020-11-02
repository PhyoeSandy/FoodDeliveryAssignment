package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.WelcomePresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.WelcomeView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
class WelcomePresenterImpl : WelcomePresenter, AbstractBasePresenter<WelcomeView>() {
    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapGettingStart() {
        mView?.navigateToIntroScreen()
    }
}