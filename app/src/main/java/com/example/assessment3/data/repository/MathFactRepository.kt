package com.example.assessment3.data.repository

import com.example.assessment3.data.remote.MathFactApiService

class MathFactRepository(
    private val apiService: MathFactApiService
) {

    suspend fun getMathFact(number: Int): String {
        return apiService
            .getMathFact(number)
            .contents
            .fact
    }
}