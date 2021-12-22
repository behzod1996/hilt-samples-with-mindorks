package com.behzoddev.hilttutorialwithmindorks.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.NonNull
import com.behzoddev.hilttutorialwithmindorks.R
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

fun ImageView.loadUrlWithPicasso(
    @NonNull url: String,
    placeHolder: Drawable = this.context.getDrawable(R.drawable.ic_launcher_foreground)!!,
    error: Drawable = this.context.getDrawable(R.drawable.ic_launcher_background)!!
) {
    Picasso.get()
        .load(url)
        .placeholder(placeHolder)
        .error(error)
        .into(this)
}

fun ImageView.loadUrlWithGlide(
    @NonNull url: String,
    placeHolder: Drawable = this.context.getDrawable(R.drawable.ic_launcher_foreground)!!,
    error: Drawable = this.context.getDrawable(R.drawable.ic_launcher_background)!!
) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .error(error)
        .placeholder(placeHolder)
        .into(this)
}