package com.example.canvart.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.canvart.data.enums.*
import com.example.canvart.data.enums.Timer
import java.util.*

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDifficulty(value: Int) = Difficulty.fromInt(value)

    @TypeConverter
    fun formDifficulty(d: Difficulty): Int {
        return d.value
    }

    @TypeConverter
    fun toMaterial(value: Int) = enumValues<Material>()[value]

    @TypeConverter
    fun fromMaterial(m: Material): Int {
        return m.value
    }

    @TypeConverter
    fun toTimer(value: Int) = enumValues<Timer>()[value]

    @TypeConverter
    fun fromTimer(t: Timer): Int {
        return t.value
    }

    @TypeConverter
    fun toType(value: Int) = enumValues<ChallengeType>()[value]

    @TypeConverter
    fun fromType(c: ChallengeType) : Int{
        return c.value
    }

    @TypeConverter
    fun toPartHead(value: Int) = enumValues<PartsHead>()[value]

    @TypeConverter
    fun fromPartHead(p: PartsHead) : Int{
        return p.value
    }

    @TypeConverter
    fun toFeature(value: Int) = enumValues<CharacterFeatures>()[value]

    @TypeConverter
    fun fromFeature(c: CharacterFeatures) : Int{
        return c.value
    }

}