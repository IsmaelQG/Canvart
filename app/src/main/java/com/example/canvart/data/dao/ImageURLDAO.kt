package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ImageURLDAO {

    @Query("SELECT url FROM image_url WHERE difficulty = 0")
    fun getAllEasyImages() : LiveData<List<String>>

    @Query("SELECT url FROM image_url WHERE difficulty = 0 ORDER BY RANDOM() LIMIT 1")
    fun getEasyImage() : LiveData<String>

}