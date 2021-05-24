package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.Drawing
import com.example.canvart.data.entity.ImageChallenge
import com.example.canvart.data.enums.Difficulty

@Dao
interface ChallengeDao {

    @Insert
    suspend fun insertChallenge(challenge: Challenge): Long

    @Query("INSERT INTO image_challenges VALUES ((:idChallenge), (:idUrl))")
    suspend fun insertImageChallengePart(idChallenge : Long, idUrl : Long): Long

    @Insert
    suspend fun insertDrawing(drawing: Drawing)

    @Delete
    suspend fun deleteChallenge(challenge: Challenge): Int

    @Delete
    suspend fun deleteChallengeList(challenge: List<Challenge>): Int

    @Query("SELECT MAX(id) FROM challenges")
    fun queryLastCustomChallengeId(): Long

    @Query("SELECT * FROM challenges WHERE type != 1 AND type != 2")
    fun queryAllCustomChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 0 AND difficulty = :difficulty")
    fun queryAllCustomChallengesByDiff(difficulty: Difficulty): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 0 AND id = :id")
    fun queryCustomChallenge(id: Long): LiveData<Challenge>

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

    @Query("SELECT * FROM drawings WHERE challenge_id = :challengeId LIMIT 1")
    fun queryDrawingByChallengeId(challengeId : Long) : Drawing

    @Query("SELECT * FROM drawings WHERE challenge_id LIKE :challengeId")
    fun queryAllDrawingsByChallengeId(challengeId : Long) : LiveData<List<Drawing>>

    @Query("SELECT * FROM image_challenges WHERE image_id = :challengeId")
    fun queryImageChallenge(challengeId : Long) : LiveData<ImageChallenge>
}