package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.canvart.data.entity.Challenge

@Dao
interface ChallengeDao {

    @Insert
    suspend fun insertChallenge(challenge: Challenge): Long

    @Delete
    suspend fun deleteChallenge(challenge: Challenge): Int

    @Delete
    suspend fun deleteChallengeList(challenge: List<Challenge>): Int

    @Query("SELECT * FROM challenges")
    fun queryAllChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE id = :id")
    fun queryAllChallenges(id: Long): List<Challenge>

    @Query("SELECT * FROM challenges WHERE state = (:state)")
    fun queryChallengesByState(state: Boolean): List<Challenge>
}