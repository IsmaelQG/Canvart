package com.example.canvart.ui.preferences

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.canvart.utils.getIntLiveData

class SettingsFragmentViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {

    val difficultyLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("difficulty", -1)

    val materialLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("material", -1)

    val timerLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("timer", -1)

    val userLevelLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("userLevel", -1)

}