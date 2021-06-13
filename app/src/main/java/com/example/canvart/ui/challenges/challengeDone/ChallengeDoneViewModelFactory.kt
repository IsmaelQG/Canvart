package com.example.canvart.ui.challenges.challengeDone

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ImageURLDAO

class ChallengeDoneViewModelFactory(private val challengeDrawingDao: ChallengeDrawingDao, private val imageURLDAO: ImageURLDAO, private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengeDoneViewModel(challengeDrawingDao, imageURLDAO, sharedPreferences) as T
}