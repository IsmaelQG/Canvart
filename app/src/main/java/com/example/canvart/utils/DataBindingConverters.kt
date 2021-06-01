package com.example.canvart.utils

import android.net.Uri
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("setImage")
    fun setImage(view : ImageView, uri: String){
        Glide.with(view.context)
                .load(Uri.parse(uri))
                .centerCrop()
                .into(view)
    }

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("setDate")
    fun setDate(view : TextView, date: Date){
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
        view.text = formatter.format(date)
    }
