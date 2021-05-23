package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.canvart.data.enums.Material
import java.util.*

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
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    @ColumnInfo(name = "challenge_id")
    val challengeId : Long,
    @ColumnInfo(name = "time_finish")
    val timeFinish : Date,
    val image : String,
    val score : Double,
    val description : String?,
    val material : Material
)