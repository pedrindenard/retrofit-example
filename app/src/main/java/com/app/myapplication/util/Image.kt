package com.app.myapplication.util

import androidx.appcompat.widget.AppCompatImageView
import com.app.myapplication.BuildConfig
import com.app.myapplication.R
import com.bumptech.glide.Glide

object Image {

    fun AppCompatImageView.loadImage(thumbnail: String) {
        val image = BuildConfig.API_TMDB_IMG + "w780" + thumbnail
        Glide.with(this)
            .load(image)
            .error(R.drawable.image_movie)
            .placeholder(R.drawable.image_movie)
            .into(this)
    }
}