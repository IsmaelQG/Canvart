package com.example.canvart.ui.challenges.imageChallenge

import android.content.SharedPreferences
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.canvart.R
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.utils.getIntLiveData
import java.util.concurrent.TimeUnit

private const val STATE_TIME = "STATE_TIME"
private const val STATE_TIMER_VALUE = "STATE_TIMER_VALUE"
private const val STATE_DIFFICULTY_VALUE = "STATE_DIFFICULTY_VALUE"
private const val STATE_URL = "STATE_URL"

class ImageChallengeViewModel(private val imageURLDAO : ImageURLDAO, private val sharedPreferences: SharedPreferences, savedStateHandle: SavedStateHandle) : ViewModel() {

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
    val urlList : LiveData<List<String>> = difficultyLiveData.switchMap {
        when (it) {
            0 -> imageURLDAO.getImagesByDiff(Difficulty.EASY)
            1 -> imageURLDAO.getImagesByDiff(Difficulty.MEDIUM)
            2 -> imageURLDAO.getImagesByDiff(Difficulty.HARD)
            else -> imageURLDAO.getImagesByDiff(Difficulty.HARD)
        }
    }

    lateinit var url : String

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
}