package com.example.canvart.ui.challenges.challengeDone

import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.Drawing
import com.example.canvart.data.entity.PC_CH
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.utils.getIntLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.*

class ChallengeDoneViewModel(private val challengeDao: ChallengeDao, private val imageURLDAO: ImageURLDAO, private val sharedPreferences: SharedPreferences) : ViewModel() {

    val cameraChecker : MutableLiveData<Boolean> = MutableLiveData(true)

    val difficultyLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("difficulty", -1)
    val materialLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("material", -1)

    lateinit var difficulty : Difficulty
    lateinit var material : Material

    var uri : MutableLiveData<String> = MutableLiveData("")

    fun switchCamera(){
        cameraChecker.value = !cameraChecker.value!!
    }

    fun setUri(newUri: Uri){
        uri.value = newUri.toString()
    }

    fun saveChallengeImage(challenge : Challenge, url : String, score : Double, description : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                challengeDao.insertChallenge(challenge)
                challengeDao.insertImageChallenge(challengeDao.queryLastCustomChallengeId(), imageURLDAO.getImageIdFromUrl(url))
                challengeDao.insertDrawing(
                        Drawing(
                                0,
                                challengeDao.queryLastCustomChallengeId(),
                                Date(),
                                uri.value!!,
                                score,
                                description,
                                material
                        )
                )
            }
        }
    }

    fun saveChallengePortrait(challenge : Challenge, listId : List<Int>, score : Double, description : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                challengeDao.insertChallenge(challenge)
                challengeDao.insertPortraitChallenge(challengeDao.queryLastCustomChallengeId())
                challengeDao.insertDrawing(
                        Drawing(
                                0,
                                challengeDao.queryLastCustomChallengeId(),
                                Date(),
                                uri.value!!,
                                score,
                                description,
                                material
                        )
                )
                for(id in listId){
                    challengeDao.insertForeignKeysHeadParts(id.toLong(), challengeDao.queryLastCustomChallengeId())
                }

            }
        }
    }

    fun saveChallengeDescription(challenge : Challenge, listId : List<Int>, score : Double, description : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                challengeDao.insertChallenge(challenge)
                challengeDao.insertDescriptionChallenge(challengeDao.queryLastCustomChallengeId())
                challengeDao.insertDrawing(
                        Drawing(
                                0,
                                challengeDao.queryLastCustomChallengeId(),
                                Date(),
                                uri.value!!,
                                score,
                                description,
                                material
                        )
                )
                for(id in listId){
                    challengeDao.insertForeignKeysDescriptionParts(id.toLong(), challengeDao.queryLastCustomChallengeId())
                }

            }
        }
    }

}