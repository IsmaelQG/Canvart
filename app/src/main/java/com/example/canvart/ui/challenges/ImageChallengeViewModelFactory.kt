package com.example.canvart.ui.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.databinding.FragmentImageChallengeBinding

class ImageChallengeViewModelFactory(private val binding : FragmentImageChallengeBinding) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ImageChallengeViewModel(binding) as T
}