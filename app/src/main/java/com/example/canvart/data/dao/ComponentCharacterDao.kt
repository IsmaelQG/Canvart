package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.canvart.data.entity.ComponentCharacter
import com.example.canvart.data.enums.Difficulty

@Dao
interface ComponentCharacterDao {

    @Query("SELECT * FROM components_character WHERE character_feature = 0 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter0(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 1 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter1(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 2 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter2(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 3 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter3(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 4 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter4(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 5 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter5(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 6 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter6(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 7 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter7(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 8 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter8(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 9 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter9(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 10 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter10(difficulty: Difficulty) : LiveData<ComponentCharacter>

    @Query("SELECT * FROM components_character WHERE character_feature = 11 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsCharacter11(difficulty: Difficulty) : LiveData<ComponentCharacter>

}