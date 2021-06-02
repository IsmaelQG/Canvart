package com.example.canvart.ui.tutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.ui.adventure.AdventureViewModel

class TutorialViewModelFactory(private val challengeDao: ChallengeDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TutorialViewModel(challengeDao) as T
}