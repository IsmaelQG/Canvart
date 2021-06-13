package com.example.canvart.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.canvart.data.enums.ChallengeType
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.data.enums.Timer

@Entity(tableName = "challenges")
data class Challenge(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val difficulty : Difficulty,
    val material : Material,
    val timer : Timer,
    val title : String,
    val description : String,
    val type : ChallengeType,
    val index : Int?
)