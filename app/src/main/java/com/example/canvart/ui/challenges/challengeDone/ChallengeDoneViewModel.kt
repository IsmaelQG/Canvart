package com.example.canvart.ui.challenges.challengeDone

import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.Drawing
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.data.enums.Timer
import com.example.canvart.utils.getIntLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ChallengeDoneViewModel(private val challengeDrawingDao: ChallengeDrawingDao, private val imageURLDAO: ImageURLDAO, private val sharedPreferences: SharedPreferences) : ViewModel() {

    val cameraChecker : MutableLiveData<Boolean> = MutableLiveData(true)

    val difficultyLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("difficulty", -1)
    val materialLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("material", -1)
    val timerLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("timer", -1)

    lateinit var difficulty : Difficulty
    lateinit var material : Material
    lateinit var timer : Timer

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
                challengeDrawingDao.insertChallenge(challenge)
                challengeDrawingDao.insertImageChallenge(challengeDrawingDao.queryLastCustomChallengeId(), imageURLDAO.getImageIdFromUrl(url))
                challengeDrawingDao.insertDrawing(
                        Drawing(
                                0,
                                challengeDrawingDao.queryLastCustomChallengeId(),
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

    fun redoChallengeImage(idChallenge : Long, score : Double, description : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                challengeDrawingDao.insertDrawing(
                    Drawing(
                        0,
                        idChallenge,
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
                challengeDrawingDao.insertChallenge(challenge)
                challengeDrawingDao.insertPortraitChallenge(challengeDrawingDao.queryLastCustomChallengeId())
                challengeDrawingDao.insertDrawing(
                        Drawing(
                                0,
                                challengeDrawingDao.queryLastCustomChallengeId(),
                                Date(),
                                uri.value!!,
                                score,
                                description,
                                material
                        )
                )
                for(id in listId){
                    challengeDrawingDao.insertForeignKeysHeadParts(id.toLong(), challengeDrawingDao.queryLastCustomChallengeId())
                }

            }
        }
    }

    fun redoChallengePortrait(idChallenge : Long, score : Double, description : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                challengeDrawingDao.insertDrawing(
                    Drawing(
                        0,
                        idChallenge,
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

    fun saveChallengeDescription(challenge : Challenge, listId : List<Int>, score : Double, description : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                challengeDrawingDao.insertChallenge(challenge)
                challengeDrawingDao.insertDescriptionChallenge(challengeDrawingDao.queryLastCustomChallengeId())
                challengeDrawingDao.insertDrawing(
                    Drawing(
                        0,
                        challengeDrawingDao.queryLastCustomChallengeId(),
                        Date(),
                        uri.value!!,
                        score,
                        description,
                        material
                    )
                )
                for(id in listId){
                    challengeDrawingDao.insertForeignKeysDescriptionParts(id.toLong(), challengeDrawingDao.queryLastCustomChallengeId())
                }
            }
        }
    }

    fun redoChallengeDescription(idChallenge : Long, score : Double, description : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                challengeDrawingDao.insertDrawing(
                    Drawing(
                        0,
                        idChallenge,
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

}