package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "drawings",
    foreignKeys = [
        ForeignKey(
            entity = Challenge::class,
            parentColumns = ["id"],
            childColumns = ["challenge_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Drawing(
    @PrimaryKey
    val id : Long,
    @ColumnInfo(name = "challenge_id")
    val challengeId : Long,
    @ColumnInfo(name = "time_start")
    val timeStart : Long,
    @ColumnInfo(name = "time_finish")
    val timeFinish : Long,
    val image : String,
    val score : Int,
    val description : String?,
    val material : Int
)