package com.example.canvart.ui.challenges.challengeShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ImageURLDAO

class ChallengeShowViewModelFactory(private val challengeDao: ChallengeDao, private val imageURLDAO: ImageURLDAO, private val challengeId : Long) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengeShowViewModel(challengeDao, imageURLDAO, challengeId) as T
}