package com.example.canvart.ui.challenges

import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.databinding.FragmentImageChallengeBinding

class ImageChallengeViewModelFactory(private val imageURLDAO: ImageURLDAO, private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ImageChallengeViewModel(imageURLDAO, sharedPreferences) as T
}