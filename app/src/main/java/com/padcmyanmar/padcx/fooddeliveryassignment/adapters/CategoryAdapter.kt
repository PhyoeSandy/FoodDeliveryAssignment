package com.padcmyanmar.padcx.fooddeliveryassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.fooddeliveryassignment.views.viewholders.CategoryViewholder
import com.padcmyanmar.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
class CategoryAdapter : BaseRecyclerAdapter<CategoryViewholder, CategoryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewholder(view)
    }
}