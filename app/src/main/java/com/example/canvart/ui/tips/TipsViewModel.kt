package com.example.canvart.ui.tips

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canvart.data.dao.TipDao
import com.example.canvart.data.entity.Tip
import com.example.canvart.utils.getIntLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TipsViewModel(private val tipDao: TipDao, private val sharedPreferences: SharedPreferences) : ViewModel() {

    val levelLiveData : LiveData<Int> = sharedPreferences.getIntLiveData("userLevel", -1)

    val tips : LiveData<List<Tip>> = tipDao.getAllTips()

    fun changeTipVisibility(id: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                tipDao.changeTipVisibility(id)
            }
        }
    }

    fun hideAllTips(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                tipDao.setAllTipsToHidden()
            }
        }
    }

}