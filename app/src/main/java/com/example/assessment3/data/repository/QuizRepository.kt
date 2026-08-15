package com.example.assessment3.data.repository

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