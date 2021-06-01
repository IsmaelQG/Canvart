package com.example.canvart.ui.challenges.challengeShowDescription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ComponentCharacterDao
import com.example.canvart.data.dao.ComponentHeadDao

class ChallengeShowDescriptionViewModelFactory(private val challengeDao: ChallengeDao, private val componentCharacterDao: ComponentCharacterDao, private val challengeId : Long) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengeShowDescriptionViewModel(challengeDao, componentCharacterDao, challengeId) as T
}