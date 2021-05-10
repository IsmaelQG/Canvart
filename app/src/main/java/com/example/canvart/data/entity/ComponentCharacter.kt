package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.canvart.data.enums.CharacterFeatures

@Entity(tableName = "components_character")
data class ComponentCharacter(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    @ColumnInfo(name = "character_feature")
    val characterFeature : CharacterFeatures,
    val text : String,
    val difficulty: Int
)