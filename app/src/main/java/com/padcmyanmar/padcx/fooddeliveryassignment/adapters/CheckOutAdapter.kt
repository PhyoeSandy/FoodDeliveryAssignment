package com.padcmyanmar.padcx.fooddeliveryassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.CheckoutViewItemActionDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders.CheckoutViewholder
import com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders.FoodDetailsViewHolder
import com.padcmyanmar.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/2/2020.
 */
class CheckOutAdapter(private val mDelegate: CheckoutViewItemActionDelegate) :
    BaseRecyclerAdapter<CheckoutViewholder, FoodVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewholder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_checkout, parent, false)
        return CheckoutViewholder(view, mDelegate)

    }
}