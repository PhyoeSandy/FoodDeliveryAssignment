package com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders

import android.net.Uri
import android.view.View
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.fooddeliveryassignment.utils.load
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
class CategoryViewholder(itemView: View) : BaseViewHolder<CategoryVO>(itemView) {
    override fun bindData(data: CategoryVO) {
        mData = data

        itemView.tvCategory.text = data.name
        itemView.ivCategory.load(Uri.parse(data.image))
    }
}