package com.example.canvart.ui.challenges.challengeShowPortrait

import androidx.lifecycle.*
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ComponentHeadDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.ComponentHead
import com.example.canvart.data.entity.Drawing
import com.example.canvart.data.entity.PortraitChallenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChallengeShowPortraitViewModel(private val challengeDao: ChallengeDao, private val componentHeadDao: ComponentHeadDao, private val challengeId : Long) : ViewModel() {

    val drawings : LiveData<List<Drawing>> = challengeDao.queryAllDrawingsByChallengeId(challengeId)

    val challenge : LiveData<Challenge> = challengeDao.queryCustomChallenge(challengeId)

    val listPortraitId : LiveData<List<Long>> = componentHeadDao.queryPortraitComponentsIdByChallengeId(challengeId)

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
}