package com.example.canvart.ui.challenges.challengesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDrawingDao

class ChallengesViewModelFactory(private val challengeDrawingDao: ChallengeDrawingDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengesViewModel(challengeDrawingDao) as T
}