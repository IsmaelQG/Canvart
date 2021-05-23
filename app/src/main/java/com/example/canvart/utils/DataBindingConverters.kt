package com.example.canvart.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

    @BindingAdapter("setImage")
    fun setImage(view : ImageView, uri: String){
        Glide.with(view.context)
                .load(Uri.parse(uri))
                .centerCrop()
                .into(view)
    }
