package com.example.josimar.framework.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoad {

    fun load(imageView: ImageView, imageUrl: String, @DrawableRes fallback: Int)

}