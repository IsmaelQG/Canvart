package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.ImageChallenge

@Dao
interface ChallengeDao {

    @Insert
    suspend fun insertChallenge(challenge: Challenge): Long

    @Query("INSERT INTO image_challenges VALUES ((:idChallenge), (:idUrl))")
    suspend fun insertImageChallengePart(idChallenge : Long, idUrl : Long): Long

    @Delete
    suspend fun deleteChallenge(challenge: Challenge): Int

    @Delete
    suspend fun deleteChallengeList(challenge: List<Challenge>): Int

    @Query("SELECT * FROM challenges WHERE type != 1 AND type != 2")
    fun queryAllCustomChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 0 AND id = :id")
    fun queryAllCustomChallenges(id: Long): List<Challenge>

    @Query("SELECT * FROM challenges WHERE type = 1")
    fun queryAllAdventureChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 1 AND id = :id")
    fun queryAllAdventureChallenges(id: Long): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 2")
    fun queryAllTutorialChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 2 AND id = :id")
    fun queryAllTutorialChallenges(id: Long): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE state = (:state)")
    fun queryChallengesByState(state: Boolean): List<Challenge>

    @Transaction
    suspend fun insertImageChallenge(challenge: Challenge, idUrl : Long){
        insertChallenge(challenge)
        insertImageChallengePart(challenge.id, idUrl)
    }
}