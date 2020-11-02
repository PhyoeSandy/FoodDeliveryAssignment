package com.padcmyanmar.padcx.fooddeliveryassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.RestaurantItemDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders.RestaurantViewholder
import com.padcmyanmar.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
class RestaurantAdapter(private val mDelegate: RestaurantItemDelegate, private val mViewType: Int) :
    BaseRecyclerAdapter<RestaurantViewholder, RestaurantVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewholder {
        return when(mViewType){
            0 -> {val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant_big,parent,false)
                RestaurantViewholder(mDelegate,view)}

            1 -> {val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant_small,parent,false)
                RestaurantViewholder(mDelegate,view)}

            else ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant_big,parent,false)
                RestaurantViewholder(mDelegate,view)
            }
        }
    }

}