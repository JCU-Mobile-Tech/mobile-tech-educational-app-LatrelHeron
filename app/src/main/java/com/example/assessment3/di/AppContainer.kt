package com.example.assessment3.di

import android.content.Context
import androidx.room.Room
import com.example.assessment3.data.local.AppDatabase
import com.example.assessment3.data.repository.QuizRepository
import com.example.assessment3.data.preferences.SettingsRepository
import com.example.assessment3.data.remote.MathFactApiService
import com.example.assessment3.data.repository.MathFactRepository
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class AppContainer(
    context: Context
) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.math.tools/")
        .addConverterFactory(
            ScalarsConverterFactory.create()
        )
        .build()
    private val mathFactApiService =
        retrofit.create(
            MathFactApiService::class.java
        )
    val mathFactRepository =
        MathFactRepository(
            mathFactApiService
        )
    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "maths_practice_database"
    ).build()

    val quizRepository = QuizRepository(
        database.quizAttemptDao()
    )

    val settingsRepository = SettingsRepository(context)
}