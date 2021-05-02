package com.example.canvart.data.enums

import androidx.room.TypeConverter

enum class Material(private val value: Int) {
    PENCIL(0), PEN(1), MARKER(2);

    @TypeConverter
    fun fromValue(value: Int): Material?{
        for(m in values()){
            if(m.value == value){
                return m
            }
        }
        return null
    }

    @TypeConverter
    fun fromMaterial(m: Material): Int{
        return m.value
    }

}