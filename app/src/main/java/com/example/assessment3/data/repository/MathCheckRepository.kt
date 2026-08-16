package com.example.assessment3.data.repository

import com.example.assessment3.data.remote.MathCheckApiService

class MathCheckRepository(
    private val apiService: MathCheckApiService
) {

    suspend fun checkMath(expression: String): String {
        return apiService
            .checkMath(expression)
    }
}