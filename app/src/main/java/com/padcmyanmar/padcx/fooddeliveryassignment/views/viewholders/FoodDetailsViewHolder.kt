package com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders


import android.view.View
import androidx.core.net.toUri
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.FoodItemDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.utils.load
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_foods_details.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/2/2020.
 */
class FoodDetailsViewHolder(itemView: View, private val mDelegate: FoodItemDelegate) :
    BaseViewHolder<FoodVO>(itemView) {

    override fun bindData(data: FoodVO) {
        mData = data

        data?.let {
            itemView.tv_restaurant_name.text = data?.name
            //itemView.tv_restaurant_description.text = data?.
            //itemView.tv_restaurant_rating.text =data?.
            itemView.tv_restaurant_price.text = data?.price.toString() + " $"
            itemView.img_restaurant.load(it.image.toUri())

            itemView.setOnClickListener {
                mData?.let {
                    mDelegate.onTapFoodItem(it)
                }
            }
        }
    }
}