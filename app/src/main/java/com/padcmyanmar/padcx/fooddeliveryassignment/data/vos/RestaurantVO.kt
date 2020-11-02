package com.padcmyanmar.padcx.fooddeliveryassignment.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
@IgnoreExtraProperties
data class RestaurantVO(
    var id: String? = "",
    var name: String? = "",
    var address: String? = "",
    var image: String? = "",
    var ratings: Double? = 0.0,
    var rating_count: Int = 0,
    var type: String? = "",
    var foodItems: ArrayList<FoodVO> = arrayListOf()
)
