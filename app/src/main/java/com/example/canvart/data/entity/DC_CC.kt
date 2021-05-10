package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "description_component_head",
    foreignKeys = [
        ForeignKey(
            entity = ComponentCharacter::class,
            parentColumns = ["id"],
            childColumns = ["component_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DescriptionChallenge::class,
            parentColumns = ["description_id"],
            childColumns = ["description_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["component_id", "description_id"]
)
data class DC_CC(
    @ColumnInfo(name = "component_id")
    val componentId : Long,
    @ColumnInfo(name = "description_id")
    val descriptionId : Long
)