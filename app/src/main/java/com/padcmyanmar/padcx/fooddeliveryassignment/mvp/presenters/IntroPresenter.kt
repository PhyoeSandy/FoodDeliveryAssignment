package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters

import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.IntroView
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

interface IntroPresenter : BasePresenter<IntroView> {
    fun onTapCreateAccount()
    fun onTapLogin()
}