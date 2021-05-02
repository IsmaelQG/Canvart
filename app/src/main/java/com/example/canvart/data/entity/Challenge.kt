package com.example.canvart.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenges")
data class Challenge(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val difficulty : Int,
    val attempts : Int,
    val state : Boolean,
    val title : String
)