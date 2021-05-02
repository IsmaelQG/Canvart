package com.example.canvart.data.enums

import androidx.room.TypeConverter

enum class PartsHead(private val value: Int) {
    GENDER(0), AGE(1), SHAPE(2), HAIR(3), EYES(4), NOSE(5), MOUTH(6), CHIN(7), NECK(8);

    @TypeConverter
    fun fromValue(value: Int): PartsHead?{
        for(p in values()){
            if(p.value == value){
                return p
            }
        }
        return null
    }

    @TypeConverter
    fun fromPartHead(p: PartsHead): Int{
        return p.value
    }

}