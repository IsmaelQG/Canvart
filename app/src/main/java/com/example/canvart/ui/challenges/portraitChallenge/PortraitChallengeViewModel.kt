package com.example.canvart.ui.challenges.portraitChallenge

import android.content.SharedPreferences
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.canvart.R
import com.example.canvart.data.dao.ComponentHeadDao
import com.example.canvart.data.entity.ComponentHead
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.utils.getIntLiveData
import java.util.concurrent.TimeUnit

private const val STATE_TIME = "STATE_TIME"
private const val STATE_TIMER_VALUE = "STATE_TIMER_VALUE"
private const val STATE_DIFFICULTY_VALUE = "STATE_DIFFICULTY_VALUE"
private const val STATE_URL = "STATE_URL"

class PortraitChallengeViewModel(private val componentHeadDao: ComponentHeadDao, private val sharedPreferences: SharedPreferences, savedStateHandle: SavedStateHandle) : ViewModel(){

    private val _timerMillis : MutableLiveData<Long>
            = savedStateHandle.getLiveData(STATE_TIME, 0)
    val timerMillis : LiveData<Long>
        get() = _timerMillis
    private val _difficultyLiveData : MutableLiveData<Int>
            = savedStateHandle.getLiveData(STATE_DIFFICULTY_VALUE, sharedPreferences.getInt("difficulty", -1))
    val difficultyLiveData : LiveData<Int>
        get() = _difficultyLiveData
    val materialLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("material", -1)
    private val _timerLiveData : MutableLiveData<Int>
            = savedStateHandle.getLiveData(STATE_TIMER_VALUE, sharedPreferences.getInt("timer", -1))
    val timerLiveData : LiveData<Int>
        get() = _timerLiveData
    val component0 : LiveData<ComponentHead> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentHeadDao.queryComponentsHead0(Difficulty.EASY)
            1 -> componentHeadDao.queryComponentsHead0(Difficulty.MEDIUM)
            2 -> componentHeadDao.queryComponentsHead0(Difficulty.HARD)
            else -> componentHeadDao.queryComponentsHead0(Difficulty.EASY)
        }
    }
    val component1 : LiveData<ComponentHead> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentHeadDao.queryComponentsHead1(Difficulty.EASY)
            1 -> componentHeadDao.queryComponentsHead1(Difficulty.MEDIUM)
            2 -> componentHeadDao.queryComponentsHead1(Difficulty.HARD)
            else -> componentHeadDao.queryComponentsHead1(Difficulty.EASY)
        }
    }
    val component2 : LiveData<ComponentHead> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentHeadDao.queryComponentsHead2(Difficulty.EASY)
            1 -> componentHeadDao.queryComponentsHead2(Difficulty.MEDIUM)
            2 -> componentHeadDao.queryComponentsHead2(Difficulty.HARD)
            else -> componentHeadDao.queryComponentsHead2(Difficulty.EASY)
        }
    }
    val component3 : LiveData<ComponentHead> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentHeadDao.queryComponentsHead3(Difficulty.EASY)
            1 -> componentHeadDao.queryComponentsHead3(Difficulty.MEDIUM)
            2 -> componentHeadDao.queryComponentsHead3(Difficulty.HARD)
            else -> componentHeadDao.queryComponentsHead3(Difficulty.EASY)
        }
    }
    val component4 : LiveData<ComponentHead> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentHeadDao.queryComponentsHead4(Difficulty.EASY)
            1 -> componentHeadDao.queryComponentsHead4(Difficulty.MEDIUM)
            2 -> componentHeadDao.queryComponentsHead4(Difficulty.HARD)
            else -> componentHeadDao.queryComponentsHead4(Difficulty.EASY)
        }
    }
    val component5 : LiveData<ComponentHead> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentHeadDao.queryComponentsHead5(Difficulty.EASY)
            1 -> componentHeadDao.queryComponentsHead5(Difficulty.MEDIUM)
            2 -> componentHeadDao.queryComponentsHead5(Difficulty.HARD)
            else -> componentHeadDao.queryComponentsHead5(Difficulty.EASY)
        }
    }
    val component6 : LiveData<ComponentHead> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentHeadDao.queryComponentsHead6(Difficulty.EASY)
            1 -> componentHeadDao.queryComponentsHead6(Difficulty.MEDIUM)
            2 -> componentHeadDao.queryComponentsHead6(Difficulty.HARD)
            else -> componentHeadDao.queryComponentsHead6(Difficulty.EASY)
        }
    }

    var condAllFound : MutableLiveData<Int> = MutableLiveData(0)

    private lateinit var timer : CountDownTimer

    fun startTimer(int: Int){
        timer = object: CountDownTimer(getMilis(int), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timerMillis.value = millisUntilFinished
            }

            override fun onFinish() {

            }
        }
        if(int != 5){
            timer.start()
        }
    }

    fun stopTimer(){
        timer.cancel()
    }

    fun parseMillis(millis: Long) : String{
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)))
    }

    fun getMilis(int: Int) : Long{
        return when(int){
            0 -> 60000
            1 -> 120000
            2 -> 300000
            3 -> 600000
            4 -> 1800000
            else -> 6000
        }
    }

    fun getDifficulty(int: Int): String{
        return when(int){
            0 -> "Fácil"
            1 -> "Medio"
            2 -> "Difícil"
            else -> "Error"
        }
    }

    fun getMaterial(int: Int): String{
        return when(int){
            0 -> "Lápiz"
            1 -> "Bolígrafo"
            2 -> "Marcador"
            else -> "Error"
        }
    }

    fun getResidBackground(int: Int): Int{
        return when(int){
            0 -> R.drawable.rounded_border_easy
            1 -> R.drawable.rounded_border_medium
            2 -> R.drawable.rounded_border_hard
            else -> R.drawable.rounded_border_easy
        }
    }

    fun concatenate() : String{
        return component0.value?.text!! + component1.value?.text + component2.value?.text + component3.value?.text + component4.value?.text + component5.value?.text + component6.value?.text
    }

    fun sum(){
        println("Value: " + condAllFound.value)
        condAllFound.value = condAllFound.value!!+1
    }

    fun getListId() : List<Int>{
        return listOf(
                component0.value!!.id.toInt(),
                component1.value!!.id.toInt(),
                component2.value!!.id.toInt(),
                component3.value!!.id.toInt(),
                component4.value!!.id.toInt(),
                component5.value!!.id.toInt(),
                component6.value!!.id.toInt()
        )
    }

}