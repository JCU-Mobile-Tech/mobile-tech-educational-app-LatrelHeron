package com.example.assessment3.data.remote

data class MathFactResponse(
    val contents: MathFactContents
)

data class MathFactContents(
    val number: Int,
    val fact: String
)