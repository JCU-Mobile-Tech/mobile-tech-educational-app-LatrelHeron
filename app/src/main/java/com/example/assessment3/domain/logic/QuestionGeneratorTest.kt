package com.example.assessment3.domain.logic

import com.example.assessment3.domain.model.QuestionType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QuestionGeneratorTest {

    @Test
    fun generateSession_returnsTenQuestions() {
        val questions = QuestionGenerator.generateSession("Normal")

        assertEquals(10, questions.size)
    }

    @Test
    fun generateSession_returnsFiveMultiplicationQuestions() {
        val questions = QuestionGenerator.generateSession("Normal")

        val multiplicationCount = questions.count {
            it.type == QuestionType.MULTIPLICATION
        }

        assertEquals(5, multiplicationCount)
    }

    @Test
    fun generateSession_returnsFiveDivisionQuestions() {
        val questions = QuestionGenerator.generateSession("Normal")

        val divisionCount = questions.count {
            it.type == QuestionType.DIVISION
        }

        assertEquals(5, divisionCount)
    }

    @Test
    fun generatedQuestionsContainCorrectAnswerInOptions() {
        val questions = QuestionGenerator.generateSession("Normal")

        questions.forEach { question ->
            assertTrue(
                question.options.contains(question.correctAnswer)
            )
        }
    }
}