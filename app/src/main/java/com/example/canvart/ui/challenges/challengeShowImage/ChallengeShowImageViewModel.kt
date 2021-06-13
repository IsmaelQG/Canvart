package com.example.canvart.ui.challenges.challengeShowImage

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.*
import com.ceylonlabs.imageviewpopup.ImagePopup
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.Drawing
import com.example.canvart.data.entity.ImageChallenge

class ChallengeShowImageViewModel(val challengeDrawingDao: ChallengeDrawingDao, private val imageURLDAO: ImageURLDAO, private val challengeId: Long, private val context: Context) : ViewModel() {

    val drawings : LiveData<List<Drawing>> = challengeDrawingDao.queryAllDrawingsByChallengeId(challengeId)

    val challenge : LiveData<Challenge> = challengeDrawingDao.queryChallenge(challengeId)

    @SuppressLint("StaticFieldLeak")
    val imagePopup = ImagePopup(context)

    private val imageChallenge : LiveData<ImageChallenge> = challengeDrawingDao.queryImageChallenge(challengeId)

    val imgUrl : LiveData<String> = imageChallenge.switchMap {
        imageURLDAO.getImageUrlFromId(it.urlId)
    }

    fun configurePopup(){
        imagePopup.backgroundColor = Color.TRANSPARENT;  // Optional
        imagePopup.isHideCloseIcon = true; // Optional
        imagePopup.isImageOnClickClose = true; // Optional
    }

}