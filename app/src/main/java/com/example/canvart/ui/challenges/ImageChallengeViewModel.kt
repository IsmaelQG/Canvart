package com.example.canvart.ui.challenges

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canvart.data.dao.ImageURLDAO
import com.example.canvart.databinding.FragmentImageChallengeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class ImageChallengeViewModel(private val imageURLDAO : ImageURLDAO) : ViewModel() {

    private val timer : CountDownTimer = object: CountDownTimer(120000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timerMillis.value = millisUntilFinished
        }

        override fun onFinish() {

        }
    }

    val timerMillis : MutableLiveData<Long> = MutableLiveData(0)
    val urlList : LiveData<List<String>> = imageURLDAO.getAllEasyImages()
    lateinit var url : String

    fun startTimer(){
        timer.start()
    }

    fun stopTimer(){
        timer.cancel()
    }

    fun parseMillis(millis: Long) : String{
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)))
    }
}