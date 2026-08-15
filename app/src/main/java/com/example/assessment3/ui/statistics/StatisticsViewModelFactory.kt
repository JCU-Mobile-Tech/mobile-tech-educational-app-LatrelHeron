package com.example.assessment3.ui.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assessment3.data.repository.QuizRepository

class StatisticsViewModelFactory(
    private val quizRepository: QuizRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(StatisticsViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return StatisticsViewModel(
                quizRepository = quizRepository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}