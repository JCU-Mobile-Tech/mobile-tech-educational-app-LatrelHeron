package com.example.assessment3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assessment3.data.local.entities.QuizAttemptEntity

@Database(
    entities = [
        QuizAttemptEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quizAttemptDao(): QuizAttemptDao
}