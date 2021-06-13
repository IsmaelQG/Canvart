package com.example.canvart.ui.challenges.challengeShowPortrait

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.*
import com.ceylonlabs.imageviewpopup.ImagePopup
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ComponentHeadDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.ComponentHead
import com.example.canvart.data.entity.Drawing

class ChallengeShowPortraitViewModel(private val challengeDrawingDao: ChallengeDrawingDao, private val componentHeadDao: ComponentHeadDao, private val challengeId : Long, private val context: Context) : ViewModel() {

    val drawings : LiveData<List<Drawing>> = challengeDrawingDao.queryAllDrawingsByChallengeId(challengeId)

    val challenge : LiveData<Challenge> = challengeDrawingDao.queryChallenge(challengeId)

    @SuppressLint("StaticFieldLeak")
    val imagePopup = ImagePopup(context)

    private val listPortraitId : LiveData<List<Long>> = componentHeadDao.queryPortraitComponentsIdByChallengeId(challengeId)

    val listParts : LiveData<List<ComponentHead>> = listPortraitId.switchMap {
        componentHeadDao.queryComponentsHeadById(it)
    }

    fun concatenate(listComponents : List<ComponentHead>) : String{
        val sortListComponents = listComponents.sortedBy { it.partHead }
        var text = ""
        for (fragment in sortListComponents){
            text += fragment.text
        }
        return text
    }

    fun configurePopup(){
        imagePopup.backgroundColor = Color.TRANSPARENT;  // Optional
        imagePopup.isHideCloseIcon = true; // Optional
        imagePopup.isImageOnClickClose = true; // Optional
    }
}