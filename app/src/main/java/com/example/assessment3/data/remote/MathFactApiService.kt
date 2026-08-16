package com.example.assessment3.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface MathFactApiService {

    @GET("{number}/math")
    suspend fun getMathFact(
        @Path("number") number: Int
    ): String
}