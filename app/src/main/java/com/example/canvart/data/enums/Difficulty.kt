package com.example.canvart.data.enums

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

enum class Difficulty(val value: Int) {
    EASY(0), MEDIUM(1), HARD(2), ADVENTURE(3), TUTORIAL(4);

    companion object {
        private val map = values().associateBy(Difficulty::value)
        fun fromInt(difficulty: Int) = map[difficulty]

    }
}