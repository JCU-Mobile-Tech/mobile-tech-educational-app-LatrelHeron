package com.example.assessment3.domain.logic

import com.example.assessment3.domain.model.Question
import com.example.assessment3.domain.model.QuestionType


object QuestionGenerator
{
    fun generateSession(difficulty: String
    ): List<Question>
    {
        val questions = mutableListOf<Question>()
        repeat(5)
        {
            questions.add(generateMultiplicationQuestion(difficulty = difficulty))
        }
        repeat(5)
        {
            questions.add(generateDivisionQuestion(difficulty = difficulty))
        }
        return questions.shuffled()
    }
    private fun getRange(
        difficulty: String
    ): IntRange {

        return when (difficulty) {
            "Easy" -> 2..5
            "Hard" -> 2..20
            else -> 2..12
        }
    }
    private fun generateMultiplicationQuestion(difficulty: String
    ): Question
    {
        val range = getRange(difficulty)
        val first = range.random()
        val second = range.random()
        val answer = first * second
        return Question(
            text = "$first × $second",
            correctAnswer = answer,
            options = generateOptions(answer),
            type = QuestionType.MULTIPLICATION
        )
    }
    private fun generateDivisionQuestion(difficulty: String
    ): Question
    {
        val range = getRange(difficulty)
        val divisor = range.random()
        val answer = range.random()
        val dividend = divisor * answer

        return Question(
            text = "$dividend ÷ $divisor",
            correctAnswer = answer,
            options = generateOptions(answer),
            type = QuestionType.DIVISION
        )
    }
    private fun generateOptions(correctAnswer: Int): List<Int>
    {
        val answers = mutableSetOf(correctAnswer)
        while (answers.size < 4)
        {
            val offset = (-10..10).random()
            val option = correctAnswer + offset
            if (option > 0)
            {
                answers.add(option)
            }
        }
        return answers.shuffled()
    }
}