package com.example.assessment3.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MathCheckApiService {

    @GET("v4/")
    suspend fun checkMath(
        @Query("expr") expression: String
    ): String
}