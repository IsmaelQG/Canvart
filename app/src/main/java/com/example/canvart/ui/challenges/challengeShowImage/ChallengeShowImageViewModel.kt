package com.example.canvart.ui.challenges.challengeShowImage

import androidx.lifecycle.*
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.Drawing
import com.example.canvart.data.entity.ImageChallenge

class ChallengeShowImageViewModel(val challengeDao: ChallengeDao, private val imageURLDAO: ImageURLDAO, private val challengeId: Long) : ViewModel() {

    val drawings : LiveData<List<Drawing>> = challengeDao.queryAllDrawingsByChallengeId(challengeId)

    val challenge : LiveData<Challenge> = challengeDao.queryChallenge(challengeId)

    private val imageChallenge : LiveData<ImageChallenge> = challengeDao.queryImageChallenge(challengeId)

    val imgUrl : LiveData<String> = imageChallenge.switchMap {
        imageURLDAO.getImageUrlFromId(it.urlId)
    }

}