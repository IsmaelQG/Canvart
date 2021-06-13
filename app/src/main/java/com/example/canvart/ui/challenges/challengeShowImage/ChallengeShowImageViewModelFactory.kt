package com.example.canvart.ui.challenges.challengeShowImage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ImageURLDAO

class ChallengeShowImageViewModelFactory(private val challengeDrawingDao: ChallengeDrawingDao, private val imageURLDAO: ImageURLDAO, private val challengeId : Long, private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengeShowImageViewModel(challengeDrawingDao, imageURLDAO, challengeId, context) as T
}