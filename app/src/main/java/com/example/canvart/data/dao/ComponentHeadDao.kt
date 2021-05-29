package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.canvart.data.entity.ComponentHead
import com.example.canvart.data.enums.Difficulty

@Dao
interface ComponentHeadDao {

    @Query("SELECT * FROM components_head WHERE part_head = 0 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsHead0(difficulty: Difficulty) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 1 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsHead1(difficulty: Difficulty) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 2 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsHead2(difficulty: Difficulty) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 3 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsHead3(difficulty: Difficulty) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 4 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsHead4(difficulty: Difficulty) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 5 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsHead5(difficulty: Difficulty) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 6 AND difficulty = :difficulty ORDER BY RANDOM() LIMIT 1")
    fun queryComponentsHead6(difficulty: Difficulty) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 0 AND id = :id")
    fun queryComponentsHeadById0(id : Long) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 1 AND id = :id")
    fun queryComponentsHeadById1(id : Long) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 2 AND id = :id")
    fun queryComponentsHeadById2(id : Long) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 3 AND id = :id")
    fun queryComponentsHeadById3(id : Long) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 4 AND id = :id")
    fun queryComponentsHeadById4(id : Long) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 5 AND id = :id")
    fun queryComponentsHeadById5(id : Long) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE part_head = 6 AND id = :id")
    fun queryComponentsHeadById6(id : Long) : LiveData<ComponentHead>

    @Query("SELECT * FROM components_head WHERE id IN (:listId)")
    fun queryComponentsHeadById(listId : List<Long>) : LiveData<List<ComponentHead>>

    @Query("SELECT component_id FROM portrait_component_head WHERE portrait_id = :id")
    fun queryPortraitComponentsIdByChallengeId(id : Long) : LiveData<List<Long>>

}