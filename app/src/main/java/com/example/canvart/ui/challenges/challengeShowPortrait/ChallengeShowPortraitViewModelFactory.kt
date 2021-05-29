package com.example.canvart.ui.challenges.challengeShowPortrait

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ComponentCharacterDao
import com.example.canvart.data.dao.ComponentHeadDao

class ChallengeShowPortraitViewModelFactory(private val challengeDao: ChallengeDao, private val componentHeadDao: ComponentHeadDao, private val challengeId : Long) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengeShowPortraitViewModel(challengeDao, componentHeadDao, challengeId) as T
}