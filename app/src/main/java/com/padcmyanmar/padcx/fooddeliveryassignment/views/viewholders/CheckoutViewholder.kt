package com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders

import android.view.View
import androidx.core.net.toUri
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.CheckoutViewItemActionDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.FoodItemDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.utils.load
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.activity_checkout.view.*
import kotlinx.android.synthetic.main.item_order.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
class CheckoutViewholder(itemView: View, private val mDelegate: CheckoutViewItemActionDelegate) :
    BaseViewHolder<FoodVO>(itemView) {

    override fun bindData(data: FoodVO) {

        data?.let {
            itemView.tvOrderItem.text = data?.name
            itemView.tvCount.text = " x ${data?.quantity}"
            itemView.tvPrice.text = "Total ${data?.totalAmount.toString()} "
/*
            data?.image?.let {
                itemView.ivRestaurant.load(it.toUri())
            }*/
        }
    }
}