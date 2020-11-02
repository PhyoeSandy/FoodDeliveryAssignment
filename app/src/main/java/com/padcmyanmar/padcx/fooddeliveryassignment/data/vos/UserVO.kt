package com.padcmyanmar.padcx.fooddeliveryassignment.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
@IgnoreExtraProperties
data class UserVO(
    var name: String? = "",
    var email: String? = "",
    var password: String? = "",
    var phone: String? = "",
    var image : String?= ""
)