package com.example.canvart.ui.adventure

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDrawingDao

class AdventureViewModelFactory(private val challengeDrawingDao: ChallengeDrawingDao, private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = AdventureViewModel(challengeDrawingDao, sharedPreferences) as T
}