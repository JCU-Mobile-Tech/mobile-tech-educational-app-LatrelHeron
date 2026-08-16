package com.example.assessment3.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assessment3.data.repository.QuizRepository
import com.example.assessment3.data.preferences.SettingsRepository

class LandingViewModelFactory(
    private val quizRepository: QuizRepository,
    private val settingsRepository: SettingsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(LandingViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return LandingViewModel(
                quizRepository = quizRepository,
                settingsRepository = settingsRepository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}