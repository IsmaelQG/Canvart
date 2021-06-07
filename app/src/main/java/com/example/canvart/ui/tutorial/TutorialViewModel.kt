package com.example.canvart.ui.tutorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.entity.Challenge

class TutorialViewModel(private val challengeDao: ChallengeDao) : ViewModel() {

    var challenges: LiveData<List<Challenge>> = challengeDao.queryAllTutorialChallenges()

    val challengesWithDrawing: LiveData<List<Challenge>> = challengeDao.queryAllTutorialChallengesWithDrawings()

    var listIdChallengesImage: LiveData<List<Long>> = challengeDao.queryAllImageChallengesId()
    var listIdChallengesPortrait: LiveData<List<Long>> = challengeDao.queryAllPortraitChallengesId()
    var listIdChallengesDescription: LiveData<List<Long>> = challengeDao.queryAllDescriptionChallengesId()

}