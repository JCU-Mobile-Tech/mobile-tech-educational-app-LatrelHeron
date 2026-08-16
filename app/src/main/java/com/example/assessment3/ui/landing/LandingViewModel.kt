package com.example.assessment3.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment3.data.repository.QuizRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.example.assessment3.data.preferences.SettingsRepository
import kotlinx.coroutines.flow.combine

data class LandingUiState(
    val overallAccuracy: Int = 0,
    val sessionsCompleted: Int = 0,
    val difficulty: String = "Normal"
)

class LandingViewModel(
    quizRepository: QuizRepository,
    settingsRepository: SettingsRepository
) : ViewModel() {

    val uiState = combine(
        quizRepository.getAllAttempts(),
        settingsRepository.difficulty
        ) { attempts, difficulty ->

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
                sessionsCompleted = sessions,
                difficulty = difficulty
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LandingUiState()
        )
}