package com.example.assessment3.ui.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment3.data.repository.QuizRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class StatisticsUiState(
    val sessionsCompleted: Int = 0,
    val overallAccuracy: Int = 0,
    val multiplicationAccuracy: Int = 0,
    val divisionAccuracy: Int = 0,
    val currentRank: String = "Unranked",
    val nextRank: String = "Bronze",
    val sessionsUntilNextRank: Int = 10
)

class StatisticsViewModel(
    quizRepository: QuizRepository
) : ViewModel() {

    val uiState = quizRepository
        .getAllAttempts()
        .map { attempts ->

            val sessions = attempts.size

            val totalCorrect = attempts.sumOf {
                it.totalCorrect
            }

            val totalQuestions = attempts.sumOf {
                it.totalQuestions
            }

            val multiplicationCorrect = attempts.sumOf {
                it.multiplicationCorrect
            }

            val divisionCorrect = attempts.sumOf {
                it.divisionCorrect
            }

            val multiplicationQuestions = sessions * 5
            val divisionQuestions = sessions * 5

            val overallAccuracy =
                if (totalQuestions == 0) 0
                else (totalCorrect * 100) / totalQuestions

            val multiplicationAccuracy =
                if (multiplicationQuestions == 0) 0
                else (multiplicationCorrect * 100) / multiplicationQuestions

            val divisionAccuracy =
                if (divisionQuestions == 0) 0
                else (divisionCorrect * 100) / divisionQuestions

            val rankInfo = calculateRank(sessions)

            StatisticsUiState(
                sessionsCompleted = sessions,
                overallAccuracy = overallAccuracy,
                multiplicationAccuracy = multiplicationAccuracy,
                divisionAccuracy = divisionAccuracy,
                currentRank = rankInfo.currentRank,
                nextRank = rankInfo.nextRank,
                sessionsUntilNextRank = rankInfo.sessionsUntilNextRank
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = StatisticsUiState()
        )

    private fun calculateRank(
        sessions: Int
    ): RankInfo {

        return when {
            sessions >= 100 -> RankInfo(
                currentRank = "Netherite",
                nextRank = "Maximum Rank",
                sessionsUntilNextRank = 0
            )

            sessions >= 75 -> RankInfo(
                currentRank = "Diamond",
                nextRank = "Netherite",
                sessionsUntilNextRank = 100 - sessions
            )

            sessions >= 50 -> RankInfo(
                currentRank = "Gold",
                nextRank = "Diamond",
                sessionsUntilNextRank = 75 - sessions
            )

            sessions >= 25 -> RankInfo(
                currentRank = "Silver",
                nextRank = "Gold",
                sessionsUntilNextRank = 50 - sessions
            )

            sessions >= 10 -> RankInfo(
                currentRank = "Bronze",
                nextRank = "Silver",
                sessionsUntilNextRank = 25 - sessions
            )

            else -> RankInfo(
                currentRank = "Unranked",
                nextRank = "Bronze",
                sessionsUntilNextRank = 10 - sessions
            )
        }
    }
}

private data class RankInfo(
    val currentRank: String,
    val nextRank: String,
    val sessionsUntilNextRank: Int
)