package com.example.canvart.ui.challenges.challengesList

import androidx.lifecycle.*
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.ui.filters.ChallengeFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.locks.Condition

class ChallengesViewModel(private val challengeDao: ChallengeDao) : ViewModel() {

    val filterCond = MutableLiveData<Boolean>(false)

    private val filterDifficulty = MutableLiveData<ChallengeFilter>(ChallengeFilter.ALL)

    var condScrollUp : Int = -1

    val challenges: LiveData<List<Challenge>> = filterDifficulty.switchMap {
        when(it){
            ChallengeFilter.ALL -> challengeDao.queryAllCustomChallenges()
            ChallengeFilter.EASY -> challengeDao.queryAllCustomChallengesByDiff(Difficulty.EASY)
            ChallengeFilter.MEDIUM -> challengeDao.queryAllCustomChallengesByDiff(Difficulty.MEDIUM)
            ChallengeFilter.HARD -> challengeDao.queryAllCustomChallengesByDiff(Difficulty.HARD)
            else -> challengeDao.queryAllCustomChallenges()
        }
    }

    var listIdChallengesImage: LiveData<List<Long>> = challengeDao.queryAllImageChallengesId()
    var listIdChallengesPortrait: LiveData<List<Long>> = challengeDao.queryAllPortraitChallengesId()
    var listIdChallengesDescription: LiveData<List<Long>> = challengeDao.queryAllDescriptionChallengesId()

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

    fun changeListByDifficulty(filter : ChallengeFilter){
        filterDifficulty.value = filter
    }

}