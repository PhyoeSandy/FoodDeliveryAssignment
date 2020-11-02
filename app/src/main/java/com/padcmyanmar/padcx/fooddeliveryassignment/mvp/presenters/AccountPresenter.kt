package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters

import android.graphics.Bitmap
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.AccountView
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
interface AccountPresenter : BasePresenter<AccountView> {
    fun onTapCancel()
    fun onTapProfile()
    fun onPhotoTaken(bitmap: Bitmap)
}