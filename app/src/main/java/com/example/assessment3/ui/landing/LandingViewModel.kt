package com.example.assessment3.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment3.data.repository.QuizRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class LandingUiState(
    val overallAccuracy: Int = 0,
    val sessionsCompleted: Int = 0
)

class LandingViewModel(
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

            val accuracy =
                if (totalQuestions == 0) {
                    0
                } else {
                    (totalCorrect * 100) / totalQuestions
                }

            LandingUiState(
                overallAccuracy = accuracy,
                sessionsCompleted = sessions
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LandingUiState()
        )
}