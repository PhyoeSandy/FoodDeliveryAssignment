package com.padcmyanmar.padcx.fooddeliveryassignment.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(uri: Uri){
    Glide.with(context)
        .load(uri)
        .into(this)
}




