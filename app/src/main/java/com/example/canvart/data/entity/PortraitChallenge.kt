package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "portrait_challenges",
    foreignKeys = [
        ForeignKey(
            entity = Challenge::class,
            parentColumns = ["id"],
            childColumns = ["portrait_id"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class PortraitChallenge(
    @PrimaryKey
    @ColumnInfo(name = "portrait_id")
    val id : Long
)