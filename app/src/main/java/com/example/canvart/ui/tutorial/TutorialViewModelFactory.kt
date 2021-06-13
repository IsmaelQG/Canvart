package com.example.canvart.ui.tutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDrawingDao

class TutorialViewModelFactory(private val challengeDrawingDao: ChallengeDrawingDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TutorialViewModel(challengeDrawingDao) as T
}