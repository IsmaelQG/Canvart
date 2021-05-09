package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_url")
data class ImageURL(
        @PrimaryKey
        val id : Long,
        @ColumnInfo(name = "url")
        val imageUrl : String,
        val difficulty: Int
)