package com.example.canvart.ui.challenges.challengeShow

import androidx.lifecycle.*
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.Drawing
import com.example.canvart.data.entity.ImageChallenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChallengeShowViewModel(val challengeDao: ChallengeDao, private val imageURLDAO: ImageURLDAO, private val challengeId: Long) : ViewModel() {

    var drawings : LiveData<List<Drawing>> = challengeDao.queryAllDrawingsByChallengeId(challengeId)

    var challenge : LiveData<Challenge> = challengeDao.queryCustomChallenge(challengeId)

    var imageChallenge : LiveData<ImageChallenge> = challengeDao.queryImageChallenge(challengeId)

    var imgUrl : LiveData<String> = imageChallenge.switchMap {
        imageURLDAO.getImageUrlFromId(it.urlId)
    }

}