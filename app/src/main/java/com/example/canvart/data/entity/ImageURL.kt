package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.canvart.data.enums.Difficulty

@Entity(tableName = "image_url")
data class ImageURL(
        @PrimaryKey(autoGenerate = true)
        val id : Long,
        @ColumnInfo(name = "url")
        val imageUrl : String,
        val difficulty: Difficulty
)