package com.example.canvart.ui.challenges.portraitChallengeRedo

import android.content.SharedPreferences
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.canvart.R
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.dao.ComponentHeadDao
import com.example.canvart.data.entity.ComponentHead
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.data.enums.Timer
import java.util.concurrent.TimeUnit

private const val STATE_TIME = "STATE_TIME"
private const val STATE_TIME_INIT = "STATE_TIME_INIT"

class PortraitChallengeRedoViewModel(private val componentHeadDao: ComponentHeadDao, private val challengeDrawingDao: ChallengeDrawingDao, private val sharedPreferences: SharedPreferences, private val challengeId : Long, savedStateHandle: SavedStateHandle) : ViewModel(){

    private val _onInitTimer : MutableLiveData<Long>
            = savedStateHandle.getLiveData(STATE_TIME_INIT, 0)
    val onInitTimer : LiveData<Long>
        get() = _onInitTimer

    val initTimerObject = object: CountDownTimer(5000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            _onInitTimer.value = millisUntilFinished
        }

        override fun onFinish() {

        }
    }

    val countdownStartChecker : MutableLiveData<Boolean> = MutableLiveData(true)

    private val _timerMillis : MutableLiveData<Long>
            = savedStateHandle.getLiveData(STATE_TIME, 0)
    val timerMillis : LiveData<Long>
        get() = _timerMillis

    val difficultyLiveData : LiveData<Difficulty> = challengeDrawingDao.queryChallengeDifficulty(challengeId)
    val materialLiveData : LiveData<Material> = challengeDrawingDao.queryChallengeMaterial(challengeId)
    val timerLiveData : LiveData<Timer> = challengeDrawingDao.queryChallengeTimer(challengeId)

    private val listComponentHead : LiveData<List<Long>> = componentHeadDao.queryPortraitComponentsIdByChallengeId(challengeId)

    val listParts : LiveData<List<ComponentHead>> = listComponentHead.switchMap {
        componentHeadDao.queryComponentsHeadById(it)
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
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)))
    }

    fun parseMillisSeconds(millis: Long) : String{
        return TimeUnit.MILLISECONDS.toSeconds(millis).toString()
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
            Difficulty.ADVENTURE -> "Aventura"
            Difficulty.TUTORIAL -> "Tutorial"
        }
    }

    fun getMaterial(material: Material): String{
        return when(material){
            Material.PENCIL -> "Lápiz"
            Material.PEN -> "Bolígrafo"
            Material.MARKER -> "Marcador"
            Material.ALL -> "Cualquiera"
        }
    }

    fun getResidBackground(difficulty: Difficulty): Int{
        return when(difficulty){
            Difficulty.EASY -> R.drawable.rounded_border_easy
            Difficulty.MEDIUM -> R.drawable.rounded_border_medium
            Difficulty.HARD -> R.drawable.rounded_border_hard
            Difficulty.ADVENTURE -> R.drawable.rounded_border_adventure
            Difficulty.TUTORIAL -> R.drawable.rounded_border_tutorial
        }
    }

    fun concatenate(listComponents : List<ComponentHead>) : String{
        val sortListComponents = listComponents.sortedBy { it.partHead }
        var text = ""
        for (fragment in sortListComponents){
            text += fragment.text
        }
        return text
    }

    fun hideStartCoundown(){
        countdownStartChecker.value = false
    }

}