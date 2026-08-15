package com.example.assessment3.di

import android.content.Context
import androidx.room.Room
import com.example.assessment3.data.local.AppDatabase
import com.example.assessment3.data.repository.QuizRepository

class AppContainer(
    context: Context
) {

    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "maths_practice_database"
    ).build()

    val quizRepository = QuizRepository(
        database.quizAttemptDao()
    )
}