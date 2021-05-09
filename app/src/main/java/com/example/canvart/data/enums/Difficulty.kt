package com.example.canvart.data.enums

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
enum class Difficulty(private val value: Int) {
    EASY(0), MEDIUM(1), HARD(2);

    @TypeConverter
    fun fromValue(value: Int): Difficulty?{
        for(d in values()){
            if(d.value == value){
                return d
            }
        }
        return null
    }

    @TypeConverter
    fun fromDifficulty(d: Difficulty): Int{
        return d.value
    }

}