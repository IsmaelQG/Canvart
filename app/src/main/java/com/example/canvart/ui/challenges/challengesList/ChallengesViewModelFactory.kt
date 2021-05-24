package com.example.canvart.ui.challenges.challengesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDao

class ChallengesViewModelFactory(private val challengeDao: ChallengeDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengesViewModel(challengeDao) as T
}