package com.example.canvart.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.canvart.data.entity.*
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.data.enums.Timer

@Dao
interface ChallengeDrawingDao {

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

    @Query("INSERT INTO description_component_character VALUES (:idComponent, :idChallenge)")
    suspend fun insertForeignKeysDescriptionParts(idComponent: Long, idChallenge : Long)

    @Insert
    suspend fun insertDrawing(drawing: Drawing)

    @Delete
    suspend fun deleteChallenge(challenge: Challenge): Int

    @Query("SELECT MAX(id) FROM challenges")
    suspend fun queryLastCustomChallengeId(): Long

    @Query("SELECT * FROM challenges WHERE type = 0 ORDER BY id DESC")
    fun queryAllCustomChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 0 AND id IN (SELECT id FROM image_challenges) ORDER BY id DESC")
    fun queryAllImageChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 0 AND id IN (SELECT id FROM portrait_challenges) ORDER BY id DESC")
    fun queryAllPortraitChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 0 AND id IN (SELECT id FROM description_challenges) ORDER BY id DESC")
    fun queryAllDescriptionChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 0 AND difficulty = :difficulty ORDER BY id DESC")
    fun queryAllCustomChallengesByDiff(difficulty: Difficulty): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE id = :id")
    fun queryChallenge(id: Long): LiveData<Challenge>

    @Query("SELECT image_id FROM image_challenges")
    fun queryAllImageChallengesId(): LiveData<List<Long>>

    @Query("SELECT portrait_id FROM portrait_challenges")
    fun queryAllPortraitChallengesId(): LiveData<List<Long>>

    @Query("SELECT description_id FROM description_challenges")
    fun queryAllDescriptionChallengesId(): LiveData<List<Long>>

    @Query("SELECT * FROM challenges WHERE type = 1")
    fun queryAllAdventureChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges c WHERE type = 1 AND c.id IN (SELECT d.challenge_id FROM drawings d)")
    fun queryAllAdventureChallengesWithDrawings(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE type = 2")
    fun queryAllTutorialChallenges(): LiveData<List<Challenge>>

    @Query("SELECT * FROM challenges c WHERE type = 2 AND c.id IN (SELECT d.challenge_id FROM drawings d)")
    fun queryAllTutorialChallengesWithDrawings(): LiveData<List<Challenge>>

    @Query("SELECT * FROM drawings WHERE challenge_id = :challengeId ORDER BY id DESC LIMIT 1")
    suspend fun queryDrawingByChallengeId(challengeId : Long) : Drawing

    @Query("SELECT * FROM drawings WHERE challenge_id LIKE :challengeId")
    fun queryAllDrawingsByChallengeId(challengeId : Long) : LiveData<List<Drawing>>

    @Query("SELECT * FROM drawings WHERE challenge_id = :challengeId")
    suspend fun queryAllDrawingsByChallengeIdNotLiveData(challengeId : Long) : List<Drawing>

    @Query("SELECT * FROM image_challenges WHERE image_id = :challengeId")
    fun queryImageChallenge(challengeId : Long) : LiveData<ImageChallenge>

    @Query("SELECT * FROM portrait_challenges WHERE portrait_id = :challengeId")
    fun queryPortraitChallenge(challengeId : Long) : LiveData<PortraitChallenge>

    @Query("SELECT difficulty FROM challenges WHERE id = :id")
    fun queryChallengeDifficulty(id : Long) : LiveData<Difficulty>

    @Query("SELECT material FROM challenges WHERE id = :id")
    fun queryChallengeMaterial(id : Long) : LiveData<Material>

    @Query("SELECT timer FROM challenges WHERE id = :id")
    fun queryChallengeTimer(id : Long) : LiveData<Timer>
}