package com.example.assessment3

import com.example.assessment3.domain.logic.RankCalculator

class RankCalculatorTest {

    @Test
    fun zeroSessions_isUnranked() {
        val result = RankCalculator.calculate(0)

        assertEquals("Unranked", result.currentRank)
        assertEquals("Bronze", result.nextRank)
        assertEquals(10, result.sessionsUntilNextRank)
    }

    @Test
    fun tenSessions_isBronze() {
        val result = RankCalculator.calculate(10)

        assertEquals("Bronze", result.currentRank)
        assertEquals("Silver", result.nextRank)
        assertEquals(15, result.sessionsUntilNextRank)
    }

    @Test
    fun twentyFiveSessions_isSilver() {
        val result = RankCalculator.calculate(25)

        assertEquals("Silver", result.currentRank)
        assertEquals("Gold", result.nextRank)
        assertEquals(25, result.sessionsUntilNextRank)
    }

    @Test
    fun fiftySessions_isGold() {
        val result = RankCalculator.calculate(50)

        assertEquals("Gold", result.currentRank)
        assertEquals("Diamond", result.nextRank)
        assertEquals(25, result.sessionsUntilNextRank)
    }

    @Test
    fun seventyFiveSessions_isDiamond() {
        val result = RankCalculator.calculate(75)

        assertEquals("Diamond", result.currentRank)
        assertEquals("Netherite", result.nextRank)
        assertEquals(25, result.sessionsUntilNextRank)
    }

    @Test
    fun oneHundredSessions_isNetherite() {
        val result = RankCalculator.calculate(100)

        assertEquals("Netherite", result.currentRank)
        assertEquals("Maximum Rank", result.nextRank)
        assertEquals(0, result.sessionsUntilNextRank)
    }
}