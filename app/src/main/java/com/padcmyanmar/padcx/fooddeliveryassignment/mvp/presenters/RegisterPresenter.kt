package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters

import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.RegisterView
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapLogin()
    fun onTapCreateAccount(userName: String, email: String, password: String,phone: String)
}