package com.example.canvart.ui.adventure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDao

class AdventureViewModelFactory(private val challengeDao: ChallengeDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = AdventureViewModel(challengeDao) as T
}