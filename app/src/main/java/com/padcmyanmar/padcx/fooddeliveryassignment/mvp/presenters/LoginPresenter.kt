package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters

import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.LoginView
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
interface LoginPresenter : BasePresenter<LoginView> {
    fun onTapLogin(email: String, password: String)
    fun onTapSignup()
}