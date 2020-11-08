package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views

import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.UserVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
interface AccountView : BaseView {
    fun openGallery()
    fun closeProfile()
    fun showProfileData(user: UserVO)
    fun showProfileImage(url: String)
    fun showSuccessMessage(message: String)
}