package com.padcmyanmar.padcx.fooddeliveryassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.RestaurantItemDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders.LargeRestaurantViewholder
import com.padcmyanmar.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/2/2020.
 */
class LargeRestaurantAdapter(private val mDelegate: RestaurantItemDelegate) :
    BaseRecyclerAdapter<LargeRestaurantViewholder, RestaurantVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeRestaurantViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant_big, parent, false)
        return LargeRestaurantViewholder(mDelegate, view)
    }

}