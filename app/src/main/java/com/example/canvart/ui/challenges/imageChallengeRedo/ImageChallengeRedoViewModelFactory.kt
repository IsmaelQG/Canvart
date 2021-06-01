package com.example.canvart.ui.challenges.imageChallengeRedo

import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ImageURLDAO

class ImageChallengeRedoViewModelFactory(private val imageURLDAO: ImageURLDAO, private val challengeDao: ChallengeDao, private val sharedPreferences: SharedPreferences, private val challengeId : Long, owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle): T = ImageChallengeRedoViewModel(imageURLDAO, challengeDao, sharedPreferences, challengeId, handle) as T
}