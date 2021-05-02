package com.example.canvart.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "components_head")
data class ComponentHead(
    @PrimaryKey
    val id : Long,
    @ColumnInfo(name = "part_head")
    val partHead : Int,
    val text : String,
    val difficulty: Int
)