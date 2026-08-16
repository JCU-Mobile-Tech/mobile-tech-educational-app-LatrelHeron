package com.example.assessment3.domain.logic

import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreCalculatorTest {

    @Test
    fun calculate_returnsCorrectOverallAccuracy() {
        val result = ScoreCalculator.calculate(
            totalCorrect = 8,
            totalQuestions = 10,
            multiplicationCorrect = 4,
            divisionCorrect = 4
        )

        assertEquals(80, result.overallAccuracy)
    }

    @Test
    fun calculate_returnsCorrectMultiplicationAccuracy() {
        val result = ScoreCalculator.calculate(
            totalCorrect = 7,
            totalQuestions = 10,
            multiplicationCorrect = 5,
            divisionCorrect = 2
        )

        assertEquals(100, result.multiplicationAccuracy)
    }

    @Test
    fun calculate_returnsCorrectDivisionAccuracy() {
        val result = ScoreCalculator.calculate(
            totalCorrect = 7,
            totalQuestions = 10,
            multiplicationCorrect = 3,
            divisionCorrect = 4
        )

        assertEquals(80, result.divisionAccuracy)
    }
}