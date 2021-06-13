package com.example.canvart.ui.challenges.descriptionChallenge

import android.content.SharedPreferences
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.canvart.R
import com.example.canvart.data.dao.ComponentCharacterDao
import com.example.canvart.data.entity.ComponentCharacter
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.utils.getIntLiveData
import java.util.concurrent.TimeUnit

private const val STATE_TIME = "STATE_TIME"
private const val STATE_TIME_INIT = "STATE_TIME_INIT"
private const val STATE_TIMER_VALUE = "STATE_TIMER_VALUE"
private const val STATE_DIFFICULTY_VALUE = "STATE_DIFFICULTY_VALUE"

class DescriptionChallengeViewModel(private val componentCharacterDao: ComponentCharacterDao, private val sharedPreferences: SharedPreferences, savedStateHandle: SavedStateHandle) : ViewModel(){

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
    private val _difficultyLiveData : MutableLiveData<Int>
            = savedStateHandle.getLiveData(STATE_DIFFICULTY_VALUE, sharedPreferences.getInt("difficulty", -1))
    val difficultyLiveData : LiveData<Int>
        get() = _difficultyLiveData
    val materialLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("material", -1)
    private val _timerLiveData : MutableLiveData<Int>
            = savedStateHandle.getLiveData(STATE_TIMER_VALUE, sharedPreferences.getInt("timer", -1))
    val timerLiveData : LiveData<Int>
        get() = _timerLiveData
    val component0 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter0(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter0(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter0(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter0(Difficulty.EASY)
        }
    }
    val component1 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter1(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter1(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter1(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter1(Difficulty.EASY)
        }
    }
    val component2 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter2(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter2(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter2(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter2(Difficulty.EASY)
        }
    }
    val component3 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter3(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter3(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter3(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter3(Difficulty.EASY)
        }
    }
    val component4 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter4(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter4(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter4(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter4(Difficulty.EASY)
        }
    }
    val component5 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter5(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter5(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter5(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter5(Difficulty.EASY)
        }
    }
    val component6 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter6(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter6(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter6(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter6(Difficulty.EASY)
        }
    }
    val component7 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter7(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter7(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter7(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter7(Difficulty.EASY)
        }
    }
    val component8 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter8(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter8(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter8(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter8(Difficulty.EASY)
        }
    }
    val component9 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter9(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter9(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter9(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter9(Difficulty.EASY)
        }
    }
    val component10 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter10(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter10(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter10(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter10(Difficulty.EASY)
        }
    }
    val component11 : LiveData<ComponentCharacter> = difficultyLiveData.switchMap {
        when (it) {
            0 -> componentCharacterDao.queryComponentsCharacter11(Difficulty.EASY)
            1 -> componentCharacterDao.queryComponentsCharacter11(Difficulty.MEDIUM)
            2 -> componentCharacterDao.queryComponentsCharacter11(Difficulty.HARD)
            else -> componentCharacterDao.queryComponentsCharacter11(Difficulty.EASY)
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

    fun parseMillisSeconds(millis: Long) : String{
        return TimeUnit.MILLISECONDS.toSeconds(millis).toString()
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
            3 -> "Cualquiera"
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
        return component0.value?.text!! + component1.value?.text!! + component2.value?.text!! + component3.value?.text!! + component4.value?.text!! + component5.value?.text!! + component6.value?.text!! + component7.value?.text!! + component8.value?.text!! + component9.value?.text!! + component10.value?.text!! +component11.value?.text!!
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
                component6.value!!.id.toInt(),
                component7.value!!.id.toInt(),
                component8.value!!.id.toInt(),
                component9.value!!.id.toInt(),
                component10.value!!.id.toInt(),
                component11.value!!.id.toInt()
                )
    }

    fun hideStartCoundown(){
        countdownStartChecker.value = false
    }

}