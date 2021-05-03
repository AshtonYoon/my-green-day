package com.ashton.mygreenday.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ashton.mygreenday.R
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String){
        Glide.with(imageView.context).load(url).error(R.drawable.ic_notifications_black_24dp).into(imageView)
    }
}