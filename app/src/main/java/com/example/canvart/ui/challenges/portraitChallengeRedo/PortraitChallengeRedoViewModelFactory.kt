package com.example.canvart.ui.challenges.portraitChallengeRedo

import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ComponentHeadDao
import com.example.canvart.ui.challenges.portraitChallenge.PortraitChallengeViewModel

class PortraitChallengeRedoViewModelFactory(private val componentHeadDao: ComponentHeadDao, private val challengeDao: ChallengeDao, private val sharedPreferences: SharedPreferences, private val idChallenge : Long, owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle): T = PortraitChallengeRedoViewModel(componentHeadDao, challengeDao, sharedPreferences, idChallenge, handle) as T
}