package com.example.assessment3.domain.model

data class QuizResult(
    val totalCorrect: Int,
    val totalQuestions: Int,
    val multiplicationCorrect: Int,
    val divisionCorrect: Int
)
{
    val overallAccuracy: Int get() =
        if (totalQuestions == 0) 0
        else (totalCorrect * 100) / totalQuestions

    val multiplicationAccuracy: Int get() = (multiplicationCorrect * 100) / 5

    val divisionAccuracy: Int get() = (divisionCorrect * 100) / 5
}