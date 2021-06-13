package com.example.canvart.ui.adventure

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.utils.getIntLiveData

class AdventureViewModel(private val challengeDrawingDao: ChallengeDrawingDao, private val sharedPreferences: SharedPreferences) : ViewModel() {

    val challenges: LiveData<List<Challenge>> = challengeDrawingDao.queryAllAdventureChallenges()

    val challengesWithDrawing: LiveData<List<Challenge>> = challengeDrawingDao.queryAllAdventureChallengesWithDrawings()

    var listIdChallengesImage: LiveData<List<Long>> = challengeDrawingDao.queryAllImageChallengesId()
    var listIdChallengesPortrait: LiveData<List<Long>> = challengeDrawingDao.queryAllPortraitChallengesId()
    var listIdChallengesDescription: LiveData<List<Long>> = challengeDrawingDao.queryAllDescriptionChallengesId()

    val level: LiveData<Int> = sharedPreferences.getIntLiveData("userLevel" , -1)

    fun levelUp(level: Int){
        with(sharedPreferences.edit()){
            putInt("userLevel", level+1)
            apply()
        }
    }

}