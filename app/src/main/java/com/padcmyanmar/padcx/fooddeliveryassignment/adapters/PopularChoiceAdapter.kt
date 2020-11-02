package com.padcmyanmar.padcx.fooddeliveryassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders.PopularChoiceViewholder
import com.padcmyanmar.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
class PopularChoiceAdapter : BaseRecyclerAdapter<PopularChoiceViewholder, FoodVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularChoiceViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular_choices, parent, false)
        return PopularChoiceViewholder(view)
    }
}