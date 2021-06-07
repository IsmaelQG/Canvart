package com.example.canvart.ui.adventure

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.utils.getIntLiveData

class AdventureViewModel(private val challengeDao: ChallengeDao, private val sharedPreferences: SharedPreferences) : ViewModel() {

    val challenges: LiveData<List<Challenge>> = challengeDao.queryAllAdventureChallenges()

    val challengesWithDrawing: LiveData<List<Challenge>> = challengeDao.queryAllAdventureChallengesWithDrawings()

    var listIdChallengesImage: LiveData<List<Long>> = challengeDao.queryAllImageChallengesId()
    var listIdChallengesPortrait: LiveData<List<Long>> = challengeDao.queryAllPortraitChallengesId()
    var listIdChallengesDescription: LiveData<List<Long>> = challengeDao.queryAllDescriptionChallengesId()

    val level: LiveData<Int> = sharedPreferences.getIntLiveData("userLevel" , -1)

    fun levelUp(level: Int){
        with(sharedPreferences.edit()){
            putInt("userLevel", level+1)
            apply()
        }
    }

}