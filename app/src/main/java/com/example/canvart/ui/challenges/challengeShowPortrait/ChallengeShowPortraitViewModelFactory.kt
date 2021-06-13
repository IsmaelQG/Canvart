package com.example.canvart.ui.challenges.challengeShowPortrait

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ComponentHeadDao

class ChallengeShowPortraitViewModelFactory(private val challengeDrawingDao: ChallengeDrawingDao, private val componentHeadDao: ComponentHeadDao, private val challengeId : Long, private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ChallengeShowPortraitViewModel(challengeDrawingDao, componentHeadDao, challengeId, context) as T
}