package com.example.canvart.ui.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDao

class ChallengeDoneViewModelFactory(private val challengeDao: ChallengeDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengeDoneViewModel(challengeDao) as T
}