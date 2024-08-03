package com.ahmedkenawy.cfhtest.ui.main.home.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ahmedkenawy.cfhtest.R
import com.bumptech.glide.Glide

/**
 * Custom [BindingAdapter] for loading images into an [ImageView] using [Glide].
 *
 * This adapter is used in data binding layouts to set an image from a URL into an [ImageView].
 *
 * @param view The [ImageView] into which the image will be loaded.
 * @param url The URL of the image to be loaded. If the URL is null or empty, no image will be loaded.
 */
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

/**
 * Replaces escaped slashes in the URL with regular slashes.
 *
 * @param url The URL with escaped slashes.
 * @return The URL with regular slashes.
 */
fun unescapeUrl(url: String): String {
    return url.replace("\\/", "/")
}
