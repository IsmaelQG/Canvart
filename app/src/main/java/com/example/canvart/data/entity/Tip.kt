package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.canvart.data.enums.ChallengeType
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.data.enums.Timer

@Entity(tableName = "tips")
data class Tip(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val title : String,
    @ColumnInfo(name = "description_first")
    val descriptionFirst : String,
    @ColumnInfo(name = "resid_image_first")
    val residImageFirst : Int,
    @ColumnInfo(name = "description_last")
    val descriptionLast : String,
    @ColumnInfo(name = "resid_image_last")
    val residImageLast : Int,
    val visibility : Boolean,
    @ColumnInfo(name = "unlock_level")
    val unlockLevel : Int
)