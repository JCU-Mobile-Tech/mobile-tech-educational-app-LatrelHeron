package com.example.assessment3.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assessment3.data.repository.QuizRepository

class ActivityViewModelFactory(
    private val quizRepository: QuizRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ActivityViewModel(
                quizRepository = quizRepository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}