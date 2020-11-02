package com.padcmyanmar.padcx.fooddeliveryassignment.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
@IgnoreExtraProperties
data class FoodVO(
    var name: String = "",
    var price: Double = 0.0,
    var image: String = "",
    var popular: Boolean = false,
    var quantity: Int = 0,
    var totalAmount: Double = 0.0
)