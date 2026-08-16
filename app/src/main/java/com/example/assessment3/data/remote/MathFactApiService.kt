package com.example.assessment3.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MathFactApiService {

    @GET("numbers/fact")
    suspend fun getMathFact(
        @Query("number") number: Int
    ): MathFactResponse
}