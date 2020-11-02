package com.padcmyanmar.padcx.shared.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
fun AppCompatImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}