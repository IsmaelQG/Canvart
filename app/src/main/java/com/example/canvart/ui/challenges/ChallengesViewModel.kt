package com.example.canvart.ui.challenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChallengesViewModel : ViewModel() {

    val filterCond = MutableLiveData<Boolean>(false)

    fun changeFilterVisibility(){
        filterCond.value = filterCond.value == false
    }

}