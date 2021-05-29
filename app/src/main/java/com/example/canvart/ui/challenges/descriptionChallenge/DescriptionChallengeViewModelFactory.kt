package com.example.canvart.ui.challenges.descriptionChallenge

import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.canvart.data.dao.ComponentCharacterDao
import com.example.canvart.data.dao.ComponentHeadDao
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.ui.challenges.imageChallenge.ImageChallengeViewModel

class DescriptionChallengeViewModelFactory(private val componentCharacterDao: ComponentCharacterDao, private val sharedPreferences: SharedPreferences, owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle): T = DescriptionChallengeViewModel(componentCharacterDao, sharedPreferences, handle) as T
}