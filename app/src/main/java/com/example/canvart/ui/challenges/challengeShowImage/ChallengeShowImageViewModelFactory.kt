package com.example.canvart.ui.challenges.challengeShowImage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ImageURLDAO

class ChallengeShowImageViewModelFactory(private val challengeDao: ChallengeDao, private val imageURLDAO: ImageURLDAO, private val challengeId : Long) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengeShowImageViewModel(challengeDao, imageURLDAO, challengeId) as T
}