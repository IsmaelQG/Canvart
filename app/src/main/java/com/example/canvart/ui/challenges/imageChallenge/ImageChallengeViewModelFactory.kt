package com.example.canvart.ui.challenges.imageChallenge

import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.canvart.data.dao.ImageURLDAO

class ImageChallengeViewModelFactory(private val imageURLDAO: ImageURLDAO, private val sharedPreferences: SharedPreferences, owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle): T = ImageChallengeViewModel(imageURLDAO, sharedPreferences, handle) as T
}