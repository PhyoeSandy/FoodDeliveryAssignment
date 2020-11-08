package com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders

import android.view.View
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_order.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
class CheckoutViewholder(itemView: View) :
    BaseViewHolder<FoodVO>(itemView) {

    override fun bindData(data: FoodVO) {
        mData = data

        data?.let {
            itemView.tvOrderItem.text = data?.name
            itemView.tvCount.text = " x ${data?.quantity}"
            itemView.tvPrice.text = "${data?.price.toString()}"
        }
    }

}