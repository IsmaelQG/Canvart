package com.example.canvart.ui.challenges.descriptonChallengeRedo

import android.content.SharedPreferences
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.canvart.R
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ComponentCharacterDao
import com.example.canvart.data.entity.ComponentCharacter
import com.example.canvart.data.entity.ComponentHead
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.data.enums.Timer
import com.example.canvart.utils.getIntLiveData
import java.util.concurrent.TimeUnit

private const val STATE_TIME = "STATE_TIME"
private const val STATE_TIMER_VALUE = "STATE_TIMER_VALUE"
private const val STATE_DIFFICULTY_VALUE = "STATE_DIFFICULTY_VALUE"
private const val STATE_URL = "STATE_URL"

class DescriptionChallengeRedoViewModel(private val componentCharacterDao: ComponentCharacterDao, private val challengeDao: ChallengeDao, private val sharedPreferences: SharedPreferences, private val challengeId : Long, savedStateHandle: SavedStateHandle) : ViewModel(){

    private val _timerMillis : MutableLiveData<Long>
            = savedStateHandle.getLiveData(STATE_TIME, 0)
    val timerMillis : LiveData<Long>
        get() = _timerMillis

    val difficultyLiveData : LiveData<Difficulty> = challengeDao.queryChallengeDifficulty(challengeId)
    val materialLiveData : LiveData<Material> = challengeDao.queryChallengeMaterial(challengeId)
    val timerLiveData : LiveData<Timer> = challengeDao.queryChallengeTimer(challengeId)

    val component0 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            Difficulty.EASY -> componentCharacterDao.queryComponentsCharacter0(Difficulty.EASY)
            Difficulty.MEDIUM -> componentCharacterDao.queryComponentsCharacter0(Difficulty.MEDIUM)
            Difficulty.HARD -> componentCharacterDao.queryComponentsCharacter0(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter0(Difficulty.EASY)
        }
    }

    private val listComponentHead : LiveData<List<Long>> = componentCharacterDao.queryCharacterComponentsIdByChallengeId(challengeId)

    val listParts : LiveData<List<ComponentCharacter>> = listComponentHead.switchMap {
        componentCharacterDao.queryComponentsCharacterById(it)
    }

    private lateinit var timer : CountDownTimer

    fun startTimer(timerValue: Timer){
        timer = object: CountDownTimer(getMilis(timerValue), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timerMillis.value = millisUntilFinished
            }

            override fun onFinish() {

            }
        }
        if(timerValue != Timer.INFINITE){
            timer.start()
        }
    }

    fun stopTimer(){
        timer.cancel()
    }

    fun parseMillis(millis: Long) : String{
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(millis)))
    }

    fun getMilis(timer: Timer) : Long{
        return when(timer){
            Timer.ONE_MIN -> 60000
            Timer.TWO_MIN -> 120000
            Timer.FIVE_MIN -> 300000
            Timer.TEN_MIN -> 600000
            Timer.THIRTY_MIN -> 1800000
            Timer.INFINITE -> 99999999
        }
    }

    fun getDifficulty(difficulty: Difficulty): String{
        return when(difficulty){
            Difficulty.EASY -> "Fácil"
            Difficulty.MEDIUM -> "Medio"
            Difficulty.HARD -> "Difícil"
            else -> "Error"
        }
    }

    fun getMaterial(material: Material): String{
        return when(material){
            Material.PENCIL -> "Lápiz"
            Material.PEN -> "Bolígrafo"
            Material.MARKER -> "Marcador"
        }
    }

    fun getResidBackground(difficulty: Difficulty): Int{
        return when(difficulty){
            Difficulty.EASY -> R.drawable.rounded_border_easy
            Difficulty.MEDIUM -> R.drawable.rounded_border_medium
            Difficulty.HARD -> R.drawable.rounded_border_hard
            Difficulty.ADVENTURE -> R.drawable.rounded_border_adventure
            else -> R.drawable.rounded_border_easy
        }
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