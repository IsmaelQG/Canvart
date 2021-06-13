package com.example.canvart.ui.challenges.challengesList

import androidx.lifecycle.*
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.ui.filters.ChallengeFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChallengesViewModel(private val challengeDrawingDao: ChallengeDrawingDao) : ViewModel() {

    val filterCond = MutableLiveData(false)

    private val filterDifficulty = MutableLiveData(ChallengeFilter.ALL)

    var condScrollUp : Int = -1

    val challenges: LiveData<List<Challenge>> = filterDifficulty.switchMap {
        when(it){
            ChallengeFilter.ALL -> challengeDrawingDao.queryAllCustomChallenges()
            ChallengeFilter.EASY -> challengeDrawingDao.queryAllCustomChallengesByDiff(Difficulty.EASY)
            ChallengeFilter.MEDIUM -> challengeDrawingDao.queryAllCustomChallengesByDiff(Difficulty.MEDIUM)
            ChallengeFilter.HARD -> challengeDrawingDao.queryAllCustomChallengesByDiff(Difficulty.HARD)
            ChallengeFilter.IMAGE -> challengeDrawingDao.queryAllImageChallenges()
            ChallengeFilter.PORTRAIT -> challengeDrawingDao.queryAllPortraitChallenges()
            ChallengeFilter.DESCRIPTION -> challengeDrawingDao.queryAllDescriptionChallenges()
            else -> challengeDrawingDao.queryAllCustomChallenges()
        }
    }

    var listIdChallengesImage: LiveData<List<Long>> = challengeDrawingDao.queryAllImageChallengesId()
    var listIdChallengesPortrait: LiveData<List<Long>> = challengeDrawingDao.queryAllPortraitChallengesId()
    var listIdChallengesDescription: LiveData<List<Long>> = challengeDrawingDao.queryAllDescriptionChallengesId()

    fun deleteChallenge(challenge: Challenge){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                challengeDrawingDao.deleteChallenge(challenge)
            }
        }
    }

    fun changeFilterVisibility(){
        filterCond.value = filterCond.value == false
    }

    fun changeList(filter : ChallengeFilter){
        filterDifficulty.value = filter
    }

}