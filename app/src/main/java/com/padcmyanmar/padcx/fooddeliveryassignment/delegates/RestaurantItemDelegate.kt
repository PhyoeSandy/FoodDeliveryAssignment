package com.padcmyanmar.padcx.fooddeliveryassignment.delegates

import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
interface RestaurantItemDelegate {
    fun onTapRestaurantItem(documentId: String)
    fun onTapFoodItem(food: FoodVO)
}