package com.example.canvart.ui.challenges.portraitChallenge

import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.canvart.data.dao.ComponentHeadDao
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.ui.challenges.imageChallenge.ImageChallengeViewModel

class PortraitChallengeViewModelFactory(private val componentHeadDao: ComponentHeadDao, private val sharedPreferences: SharedPreferences, owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle): T = PortraitChallengeViewModel(componentHeadDao, sharedPreferences, handle) as T
}