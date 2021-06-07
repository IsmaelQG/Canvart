package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.canvart.data.entity.Tip

@Dao
interface TipDao {

    @Query("SELECT * FROM tips")
    fun getAllTips() : LiveData<List<Tip>>

    @Query("UPDATE tips SET visibility = NOT visibility WHERE id = :id")
    fun changeTipVisibility(id : Long)

    @Query("UPDATE tips SET visibility = 0")
    fun setAllTipsToHidden()

}