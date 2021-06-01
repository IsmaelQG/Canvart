package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.canvart.data.enums.Difficulty

@Dao
interface ImageURLDAO {

    @Query("SELECT url FROM image_url WHERE difficulty = :difficulty")
    fun getImagesByDiff(difficulty : Difficulty) : LiveData<List<String>>

    @Query("SELECT id FROM image_url WHERE url = :url")
    fun getImageIdFromUrl(url : String) : Long

    @Query("SELECT url FROM image_url WHERE id = :id")
    fun getImageUrlFromId(id : Long) : LiveData<String>

}