package com.example.assessment3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.assessment3.data.local.entities.QuizAttemptEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizAttemptDao {

    @Insert
    suspend fun insertAttempt(
        attempt: QuizAttemptEntity
    )

    @Query("SELECT * FROM quiz_attempts ORDER BY timestamp DESC")
    fun getAllAttempts(): Flow<List<QuizAttemptEntity>>

    @Query("DELETE FROM quiz_attempts")
    suspend fun deleteAllAttempts()
}