package com.padcmyanmar.padcx.fooddeliveryassignment.data.model

import android.graphics.Bitmap
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.UserVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
interface AuthenticationModel {

    fun login(email: String, password: String, onSuccess:()-> Unit, onFailure: (String)-> Unit)

    fun register(userName: String, email: String, password: String,
                 phone: String, onSuccess:()-> Unit, onFailure: (String)-> Unit )

    fun getProfileData() : UserVO

    fun uploadPhoto(bitmap: Bitmap, onSuccess:(image: String)-> Unit, onFailure: (String)-> Unit )

    fun updateProfile(imageUrl: String, onSuccess:()-> Unit, onFailure: (String)-> Unit )

}