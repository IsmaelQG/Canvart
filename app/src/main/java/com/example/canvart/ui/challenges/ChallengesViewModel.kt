package com.example.canvart.ui.challenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.enums.ChallengeType
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChallengesViewModel(private val challengeDao: ChallengeDao) : ViewModel() {

    val filterCond = MutableLiveData<Boolean>(false)

    var challenges: LiveData<List<Challenge>> = challengeDao.queryAllCustomChallenges()

    fun addChallenge(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                challengeDao.insertChallenge(
                        Challenge(
                                0,
                                Difficulty.MEDIUM,
                                Material.PENCIL,
                                1,
                                true,
                                "Lorem ipsum",
                                ChallengeType.CUSTOM,
                                null
                        )
                )
            }
        }
    }

    fun deleteChallenge(challenge: Challenge){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                challengeDao.deleteChallenge(challenge)
            }
        }
    }

    fun changeFilterVisibility(){
        filterCond.value = filterCond.value == false
    }

}