package com.example.canvart.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.canvart.data.entity.Challenge

@Dao
interface ChallengeDao {

    @Insert
    fun insertChallenge(challenge: Challenge): Long

    @Delete
    fun deleteChallenge(challenge: Challenge): Int

    @Delete
    fun deleteChallengeList(challenge: List<Challenge>): Int

    @Query("SELECT * FROM challenges")
    fun queryAllChallenges(): List<Challenge>

    @Query("SELECT * FROM challenges WHERE id = :id")
    fun queryAllChallenges(id: Long): List<Challenge>

    @Query("SELECT * FROM challenges WHERE state = (:state)")
    fun queryChallengesByState(state: Boolean): List<Challenge>
}