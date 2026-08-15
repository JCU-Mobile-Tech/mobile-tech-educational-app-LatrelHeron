package com.example.assessment3.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_attempts")
data class QuizAttemptEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val totalCorrect: Int,
    val multiplicationCorrect: Int,
    val divisionCorrect: Int,
    val totalQuestions: Int = 10,

    val timestamp: Long = System.currentTimeMillis()
)