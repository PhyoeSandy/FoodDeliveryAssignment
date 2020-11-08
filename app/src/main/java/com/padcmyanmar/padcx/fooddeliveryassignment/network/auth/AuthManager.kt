package com.padcmyanmar.padcx.fooddeliveryassignment.network.auth

import android.graphics.Bitmap
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.UserVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
interface AuthManager {
    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun register(
        userName: String, email: String, password: String,
        phone: String, onSuccess: () -> Unit, onFailure: (String) -> Unit
    )

    fun getProfile(): UserVO

    fun uploadProfileImage(bitmap: Bitmap, onSuccess: (imageUrl: String) -> Unit, onFailure: (String) -> Unit)

    fun updateProfile(imageUrl: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

}