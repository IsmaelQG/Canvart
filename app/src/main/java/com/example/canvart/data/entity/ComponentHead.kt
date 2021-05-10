package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.PartsHead

@Entity(tableName = "components_head")
data class ComponentHead(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    @ColumnInfo(name = "part_head")
    val partHead : PartsHead,
    val text : String,
    val difficulty: Difficulty
)