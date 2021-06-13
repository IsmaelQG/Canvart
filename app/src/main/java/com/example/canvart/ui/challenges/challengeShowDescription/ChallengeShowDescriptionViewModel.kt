package com.example.canvart.ui.challenges.challengeShowDescription

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.*
import com.ceylonlabs.imageviewpopup.ImagePopup
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ComponentCharacterDao
import com.example.canvart.data.entity.*

class ChallengeShowDescriptionViewModel(private val challengeDrawingDao: ChallengeDrawingDao, private val componentCharacterDao: ComponentCharacterDao, private val challengeId : Long, private val context: Context) : ViewModel() {

    val drawings : LiveData<List<Drawing>> = challengeDrawingDao.queryAllDrawingsByChallengeId(challengeId)

    val challenge : LiveData<Challenge> = challengeDrawingDao.queryChallenge(challengeId)

    @SuppressLint("StaticFieldLeak")
    val imagePopup = ImagePopup(context)


    private val listDescriptionId : LiveData<List<Long>> = componentCharacterDao.queryCharacterComponentsIdByChallengeId(challengeId)

    val listParts : LiveData<List<ComponentCharacter>> = listDescriptionId.switchMap {
        componentCharacterDao.queryComponentsCharacterById(it)
    }

    fun concatenate(listComponents : List<ComponentCharacter>) : String{
        val sortListComponents = listComponents.sortedBy { it.characterFeature }
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