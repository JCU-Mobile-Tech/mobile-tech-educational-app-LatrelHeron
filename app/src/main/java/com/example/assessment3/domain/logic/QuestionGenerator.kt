package com.example.assessment3.domain.logic

import Assessment3.package.

object QuestionGenerator
{
    fun generateSession(): List<Question>
    {
        val questions = mutableListOf<Question>()
        repeat(5)
        {
            questions.add(generateMultiplicationQuestion())
        }
        repeat(5)
        {
            questions.add(generateDivisionQuestion())
        }
        return questions.shuffled()
    }
    private fun generateMultiplicationQuestion(): Question
    {
        val first = (2..12).random()
        val second = (2..12).random()
        val answer = first * second
        return Question(
            text = "$first × $second",
            correctAnswer = answer,
            options = generateOptions(answer),
            type = QuestionType.MULTIPLICATION
        )
    }
    private fun generateDivisionQuestion(): Question
    {
        val divisor = (2..12).random()
        val answer = (2..12).random()
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
        while (answers.size < 4) {
            val offset = (-10..10).random()
            val option = correctAnswer + offset
            if (option > 0) {
                answers.add(option)
            }
        }
        return answers.shuffled()
    }
}