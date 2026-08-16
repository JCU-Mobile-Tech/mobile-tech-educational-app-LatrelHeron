package com.example.assessment3.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assessment3.data.repository.QuizRepository
import com.example.assessment3.data.repository.MathFactRepository

class ActivityViewModelFactory(
    private val quizRepository: QuizRepository,
    private val settingsRepository: SettingsRepository,
    private val mathFactRepository: MathFactRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ActivityViewModel(
                quizRepository = quizRepository,
                settingsRepository = settingsRepository,
                mathFactRepository = mathFactRepository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}