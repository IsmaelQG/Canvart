package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

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
            parentColumns = ["id"],
            childColumns = ["description_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DC_CC(
    @ColumnInfo(name = "component_id")
    val componentId : Long,
    @ColumnInfo(name = "description_id")
    val descriptionId : Long
)