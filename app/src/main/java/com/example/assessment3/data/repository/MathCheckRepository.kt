package com.example.assessment3.data.repository

import com.example.assessment3.data.remote.MathFactApiService

class MathCheckRepository(
    private val apiService: MathFactApiService
) {

    suspend fun getMathFact(number: Int): String {
        return apiService
            .getMathFact(number)
    }
}