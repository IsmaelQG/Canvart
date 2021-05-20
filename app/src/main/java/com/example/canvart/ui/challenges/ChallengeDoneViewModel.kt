package com.example.canvart.ui.challenges

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvart.data.dao.ChallengeDao

class ChallengeDoneViewModel(private val challengeDao: ChallengeDao) : ViewModel() {

    val cameraChecker : MutableLiveData<Boolean> = MutableLiveData(true)

    var uri : MutableLiveData<String> = MutableLiveData("")

    fun switchCamera(){
        cameraChecker.value = !cameraChecker.value!!
    }

    fun setUri(newUri: Uri){
        uri.value = newUri.toString()
    }

    fun getUri() : String{
        return uri.value!!
    }

}