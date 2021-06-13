package com.example.canvart.ui.challenges.challengeShowDescription

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ComponentCharacterDao

class ChallengeShowDescriptionViewModelFactory(private val challengeDrawingDao: ChallengeDrawingDao, private val componentCharacterDao: ComponentCharacterDao, private val challengeId : Long, private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengeShowDescriptionViewModel(challengeDrawingDao, componentCharacterDao, challengeId, context) as T
}