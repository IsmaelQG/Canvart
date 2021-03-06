package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
        tableName = "portrait_component_head",
        primaryKeys = ["component_id", "portrait_id"],
        foreignKeys = [
            ForeignKey(
                entity = ComponentHead::class,
                parentColumns = ["id"],
                childColumns = ["component_id"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                entity = Challenge::class,
                parentColumns = ["id"],
                childColumns = ["portrait_id"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
            )
        ]
)
data class PC_CH(
    @ColumnInfo(name = "component_id")
    val componentId : Long,
    @ColumnInfo(name = "portrait_id")
    val portraitId : Long
)