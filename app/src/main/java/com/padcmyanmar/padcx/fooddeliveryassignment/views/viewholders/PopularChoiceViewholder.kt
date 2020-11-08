package com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders

import android.view.View
import androidx.core.net.toUri
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.utils.load
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_popular_choices.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
class PopularChoiceViewholder(itemView: View) : BaseViewHolder<FoodVO>(itemView) {
    override fun bindData(data: FoodVO) {
        mData = data

        itemView.ivRestaurant.load(data.image.toUri())
        itemView.tvName.text = data.name
    }

}