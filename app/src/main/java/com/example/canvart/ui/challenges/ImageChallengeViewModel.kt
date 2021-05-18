package com.example.canvart.ui.challenges

import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.example.canvart.R
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.databinding.FragmentImageChallengeBinding
import com.example.canvart.utils.getIntLiveData
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class ImageChallengeViewModel(private val imageURLDAO : ImageURLDAO, private val sharedPreferences: SharedPreferences) : ViewModel() {

    val timerMillis : MutableLiveData<Long> = MutableLiveData(0)
    val difficultyLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("difficulty", -1)
    val materialLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("material", -1)
    val timerLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("timer", -1)
    val urlList : LiveData<List<String>> = difficultyLiveData.switchMap {
        when (it) {
            0 -> imageURLDAO.getAllEasyImages()
            1 -> imageURLDAO.getAllEasyImages()
            2 -> imageURLDAO.getAllEasyImages()
            else -> imageURLDAO.getAllEasyImages()
        }
    }

    lateinit var url : String

    private lateinit var timer : CountDownTimer

    fun startTimer(int: Int){
        timer = object: CountDownTimer(getMilis(int), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerMillis.value = millisUntilFinished
            }

            override fun onFinish() {

            }
        }
        timer.start()
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
            4 -> 900000
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