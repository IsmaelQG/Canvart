package com.example.canvart.ui.challenges.challengeShowDescription

import androidx.lifecycle.*
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ComponentCharacterDao
import com.example.canvart.data.entity.*

class ChallengeShowDescriptionViewModel(private val challengeDao: ChallengeDao, private val componentCharacterDao: ComponentCharacterDao, private val challengeId : Long) : ViewModel() {

    val drawings : LiveData<List<Drawing>> = challengeDao.queryAllDrawingsByChallengeId(challengeId)

    val challenge : LiveData<Challenge> = challengeDao.queryChallenge(challengeId)

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
}