package com.example.assessment3.domain.model

enum class QuestionType { MULTIPLICATION, DIVISION }

data class Question( val text: String, val correctAnswer: Int, val options: List<Int>, val type: QuestionType )