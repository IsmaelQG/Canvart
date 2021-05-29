package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.canvart.data.entity.*
import com.example.canvart.data.enums.Difficulty

@Dao
interface ChallengeDao {

    @Insert
    suspend fun insertChallenge(challenge: Challenge): Long

    @Query("INSERT INTO image_challenges VALUES ((:idChallenge), (:idUrl))")
    suspend fun insertImageChallenge(idChallenge : Long, idUrl : Long)

    @Query("INSERT INTO portrait_challenges VALUES (:idChallenge)")
    suspend fun insertPortraitChallenge(idChallenge : Long)

    @Query("INSERT INTO description_challenges VALUES (:idChallenge)")
    suspend fun insertDescriptionChallenge(idChallenge : Long)

    @Query("INSERT INTO portrait_component_head VALUES (:idComponent, :idChallenge)")
    suspend fun insertForeignKeysHeadParts(idComponent: Long, idChallenge : Long)

    @Query("INSERT INTO description_component_head VALUES (:idComponent, :idChallenge)")
    suspend fun insertForeignKeysDescriptionParts(idComponent: Long, idChallenge : Long)

    @Insert
    suspend fun insertDrawing(drawing: Drawing)

    @Delete
    suspend fun deleteChallenge(challenge: Challenge): Int

    @Delete
    suspend fun deleteChallengeList(challenge: List<Challenge>): Int

    @Query("SELECT MAX(id) FROM challenges")
    suspend fun queryLastCustomChallengeId(): Long

    @Query("SELECT * FROM challenges WHERE type = 0")
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

    @Query("SELECT * FROM portrait_challenges WHERE portrait_id = :challengeId")
    fun queryPortraitChallenge(challengeId : Long) : LiveData<PortraitChallenge>

    @Query("SELECT * FROM portrait_component_head")
    fun getAllFK() : List<PC_CH>
}