package com.example.assessment3.domain.logic

import com.example.assessment3.domain.model.QuizResult

object ScoreCalculator
{
    fun calculate(
        totalCorrect: Int,
        totalQuestions: Int,
        multiplicationCorrect: Int,
        divisionCorrect: Int
    ): QuizResult {
        return QuizResult(
            totalCorrect = totalCorrect,
            totalQuestions = totalQuestions,
            multiplicationCorrect = multiplicationCorrect,
            divisionCorrect = divisionCorrect
        )
    }
}