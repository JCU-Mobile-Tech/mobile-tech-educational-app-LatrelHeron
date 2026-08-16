package com.example.assessment3.domain.logic

data class RankResult(
    val currentRank: String,
    val nextRank: String,
    val sessionsUntilNextRank: Int
)

object RankCalculator {

    fun calculate(sessions: Int): RankResult {
        return when {
            sessions >= 100 -> RankResult(
                currentRank = "Netherite",
                nextRank = "Maximum Rank",
                sessionsUntilNextRank = 0
            )

            sessions >= 75 -> RankResult(
                currentRank = "Diamond",
                nextRank = "Netherite",
                sessionsUntilNextRank = 100 - sessions
            )

            sessions >= 50 -> RankResult(
                currentRank = "Gold",
                nextRank = "Diamond",
                sessionsUntilNextRank = 75 - sessions
            )

            sessions >= 25 -> RankResult(
                currentRank = "Silver",
                nextRank = "Gold",
                sessionsUntilNextRank = 50 - sessions
            )

            sessions >= 10 -> RankResult(
                currentRank = "Bronze",
                nextRank = "Silver",
                sessionsUntilNextRank = 25 - sessions
            )

            else -> RankResult(
                currentRank = "Unranked",
                nextRank = "Bronze",
                sessionsUntilNextRank = 10 - sessions
            )
        }
    }
}