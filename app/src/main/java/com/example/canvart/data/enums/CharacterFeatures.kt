package com.example.canvart.data.enums

import androidx.room.TypeConverter

enum class CharacterFeatures(private val value: Int) {
    GENDER(0), AGE(1), PERSONALITY(2), POSE(3), HAIR(4), HEAD_SHAPE(5), UPPER_BODY(6), LOWER_BODY(7), UPPER_CLOTHES(7), LOWER_CLOTHES(8), SHOES(9), OBJECTS(10), ACTION(11);

    @TypeConverter
    fun fromValue(value: Int): CharacterFeatures?{
        for(p in values()){
            if(p.value == value){
                return p
            }
        }
        return null
    }

    @TypeConverter
    fun fromCharacterFeature(p: CharacterFeatures): Int{
        return p.value
    }

}