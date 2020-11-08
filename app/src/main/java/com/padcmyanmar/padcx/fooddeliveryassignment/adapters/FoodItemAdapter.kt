package com.padcmyanmar.padcx.fooddeliveryassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.FoodItemDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders.FoodDetailsViewHolder
import com.padcmyanmar.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/2/2020.
 */
class FoodItemAdapter(private val mDelegate: FoodItemDelegate) :
    BaseRecyclerAdapter<FoodDetailsViewHolder, FoodVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_foods_details, parent, false)
        return FoodDetailsViewHolder(view, mDelegate)
    }

}