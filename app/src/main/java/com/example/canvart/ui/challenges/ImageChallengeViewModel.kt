package com.example.canvart.ui.challenges

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvart.databinding.FragmentImageChallengeBinding
import com.google.android.material.snackbar.Snackbar

class ImageChallengeViewModel(private val binding : FragmentImageChallengeBinding) : ViewModel() {

    private val timer : CountDownTimer = object: CountDownTimer(6000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timerMillis.value = millisUntilFinished
        }

        override fun onFinish() {

        }
    }

    val timerMillis : MutableLiveData<Long> = MutableLiveData<Long>(0)

    fun startTimer(){
        timer.start()
    }

    fun stopTimer(){
        timer.cancel()
    }

}