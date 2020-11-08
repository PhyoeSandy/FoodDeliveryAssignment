package com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders

import android.view.View
import androidx.core.net.toUri
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.RestaurantItemDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.utils.load
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_restaurant_small.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
class RestaurantViewholder(private val mDelegate: RestaurantItemDelegate, itemView: View) :
    BaseViewHolder<RestaurantVO>(itemView) {

    override fun bindData(data: RestaurantVO) {
        mData = data

        data.image?.let { itemView.ivRestaurant.load(it.toUri()) }
        itemView.tvName.text = data.name
        itemView.tvRatings.text = data.ratings.toString()
        itemView.tvType.text = data.type

        itemView.rlRestaurant.setOnClickListener {
            data.id?.let {
                mDelegate.onTapRestaurantItem(it)
            }
        }
    }

}