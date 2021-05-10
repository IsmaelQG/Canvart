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
import kotlin.random.Random

class ImageChallengeViewModel(private val imageURLDAO : ImageURLDAO) : ViewModel() {

    private val timer : CountDownTimer = object: CountDownTimer(6000, 1000) {
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
}