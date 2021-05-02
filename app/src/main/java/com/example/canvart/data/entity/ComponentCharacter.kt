package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "components_character")
data class ComponentCharacter(
    @PrimaryKey
    val id : Long,
    @ColumnInfo(name = "character_feature")
    val characterFeature : Int,
    val text : String,
    val difficulty: Int
)