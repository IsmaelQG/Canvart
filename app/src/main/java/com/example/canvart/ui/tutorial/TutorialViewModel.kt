package com.example.canvart.ui.tutorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.entity.Challenge

class TutorialViewModel(private val challengeDrawingDao: ChallengeDrawingDao) : ViewModel() {

    var challenges: LiveData<List<Challenge>> = challengeDrawingDao.queryAllTutorialChallenges()

    val challengesWithDrawing: LiveData<List<Challenge>> = challengeDrawingDao.queryAllTutorialChallengesWithDrawings()

    var listIdChallengesImage: LiveData<List<Long>> = challengeDrawingDao.queryAllImageChallengesId()
    var listIdChallengesPortrait: LiveData<List<Long>> = challengeDrawingDao.queryAllPortraitChallengesId()
    var listIdChallengesDescription: LiveData<List<Long>> = challengeDrawingDao.queryAllDescriptionChallengesId()

}