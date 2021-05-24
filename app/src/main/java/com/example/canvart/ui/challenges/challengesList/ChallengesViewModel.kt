package com.example.canvart.ui.challenges.challengesList

import androidx.lifecycle.*
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.ui.filters.DifficultyFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChallengesViewModel(private val challengeDao: ChallengeDao) : ViewModel() {

    val filterCond = MutableLiveData<Boolean>(false)

    val filterDifficulty = MutableLiveData<DifficultyFilter>(DifficultyFilter.ALL)

    var challenges: LiveData<List<Challenge>> = filterDifficulty.switchMap {
        when(it){
            DifficultyFilter.ALL -> challengeDao.queryAllCustomChallenges()
            DifficultyFilter.EASY -> challengeDao.queryAllCustomChallengesByDiff(Difficulty.EASY)
            DifficultyFilter.MEDIUM -> challengeDao.queryAllCustomChallengesByDiff(Difficulty.MEDIUM)
            DifficultyFilter.HARD -> challengeDao.queryAllCustomChallengesByDiff(Difficulty.HARD)
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

    fun changeListByDifficulty(filter : DifficultyFilter){
        filterDifficulty.value = filter
    }

}