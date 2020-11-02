package com.padcmyanmar.padcx.fooddeliveryassignment.data.model.impls

import android.graphics.Bitmap
import com.padcmyanmar.padcx.fooddeliveryassignment.data.model.AuthenticationModel
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.UserVO
import com.padcmyanmar.padcx.fooddeliveryassignment.network.auth.FirebaseAuthManager

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
object AuthenticationModelImpl : AuthenticationModel {
    private val mAuthManager = FirebaseAuthManager

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email, password, onSuccess, onFailure)
    }

    override fun register(
        userName: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.register(userName, email, password, phone, onSuccess, onFailure)
    }

    override fun getProfileData(): UserVO {
        return mAuthManager.getProfile()
    }

    override fun uploadPhoto(
        bitmap: Bitmap,
        onSuccess: (image: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.uploadProfileImage(bitmap, onSuccess, onFailure)
    }

    override fun updateProfile(imageUrl: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.updateProfile(imageUrl, onSuccess, onFailure)
    }
}