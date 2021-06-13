package com.example.canvart.ui.challenges.descriptonChallengeRedo

import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ComponentCharacterDao

class DescriptionChallengeRedoViewModelFactory(private val componentCharacterDao: ComponentCharacterDao, private val challengeDrawingDao: ChallengeDrawingDao, private val sharedPreferences: SharedPreferences, private val idChallenge : Long, owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = DescriptionChallengeRedoViewModel(componentCharacterDao, challengeDrawingDao, sharedPreferences, idChallenge, handle) as T
}