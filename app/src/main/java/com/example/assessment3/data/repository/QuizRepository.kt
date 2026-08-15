package com.example.assessment3.data.repository

import com.example.assessment3.data.local.QuizAttemptDao
import com.example.assessment3.data.local.entities.QuizAttemptEntity
import kotlinx.coroutines.flow.Flow

class QuizRepository(
    private val quizAttemptDao: QuizAttemptDao
) {

    fun getAllAttempts(): Flow<List<QuizAttemptEntity>> {
        return quizAttemptDao.getAllAttempts()
    }

    suspend fun saveAttempt(
        totalCorrect: Int,
        multiplicationCorrect: Int,
        divisionCorrect: Int
    ) {
        val attempt = QuizAttemptEntity(
            totalCorrect = totalCorrect,
            multiplicationCorrect = multiplicationCorrect,
            divisionCorrect = divisionCorrect
        )

        quizAttemptDao.insertAttempt(attempt)
    }

    suspend fun resetProgress() {
        quizAttemptDao.deleteAllAttempts()
    }
}