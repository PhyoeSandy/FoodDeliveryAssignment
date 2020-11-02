package com.padcmyanmar.padcx.fooddeliveryassignment.delegates

import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO

interface FoodItemDelegate {
    fun onTapFoodItem(foodItem: FoodVO)
}