package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.impls.AuthenticationModelImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.AccountPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.AccountView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
class AccountPresenterImpl : AccountPresenter, AbstractBasePresenter<AccountView>() {
    private val mAuthenticationModel = AuthenticationModelImpl

    override fun onTapCancel() {
        mView?.closeProfile()
    }

    override fun onTapProfile() {
        mView?.openGallery()
    }

    override fun onPhotoTaken(bitmap: Bitmap) {
        mAuthenticationModel.uploadPhoto(bitmap, {
            mAuthenticationModel.updateProfile(it, {
                mView?.showSuccessMessage("Profile successfully updated.")
                mView?.showProfileImage(it)
            }, {
                mView?.showErrorMessage(it)
            })
        }, {
            mView?.showErrorMessage(it)
        })
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mView?.showProfileData(
            mAuthenticationModel.getProfileData()
        )
    }
}