package com.example.canvart.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material

@Entity(tableName = "challenges")
data class Challenge(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val difficulty : Int,
    val material : Int,
    val attempts : Int,
    val state : Boolean,
    val title : String
)