package com.example.canvart.ui.tips

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.TipDao
import com.example.canvart.ui.tutorial.TutorialViewModel

class TipsViewModelFactory(private val tipDao: TipDao, private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TipsViewModel(tipDao, sharedPreferences) as T
}