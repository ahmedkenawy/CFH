package com.ahmedkenawy.cfhtest.ui.main.home.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ahmedkenawy.cfhtest.R
import com.bumptech.glide.Glide
import kotlin.math.log

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    val modifiedUrl = unescapeUrl(url ?: "")
    Log.d("TAG", "loadImage:Kenawy $modifiedUrl")
    if (url.isNullOrEmpty().not()) {
        Glide.with(view.context)
            .load(modifiedUrl)
            .error(R.drawable.ic_launcher_background)
            .into(view)
    }
}

fun unescapeUrl(url: String): String {
    return url.replace("\\/", "/")
}
