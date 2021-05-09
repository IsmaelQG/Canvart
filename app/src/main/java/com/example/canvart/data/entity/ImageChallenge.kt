package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "image_challenges",
    foreignKeys = [
        ForeignKey(
            entity = Challenge::class,
            parentColumns = ["id"],
            childColumns = ["image_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
                entity = ImageURL::class,
                parentColumns = ["id"],
                childColumns = ["url_id"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ImageChallenge(
    @PrimaryKey
    @ColumnInfo(name = "image_id")
    val id : Long,
    @ColumnInfo(name = "url_id")
    val urlId : Long
)