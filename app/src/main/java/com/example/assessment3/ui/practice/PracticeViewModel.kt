package com.example.assessment3.ui.practice

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.assessment3.domain.model.Question
import com.example.assessment3.domain.model.QuestionType

data class PracticeUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswer: Int? = null,
    val answerSubmitted: Boolean = false,
    val practiceFinished: Boolean = false
) {
    val currentQuestion: Question?
        get() = questions.getOrNull(currentQuestionIndex)
}

class PracticeViewModel : ViewModel() {

    var uiState = mutableStateOf(
        PracticeUiState(
            questions = generatePracticeQuestions()
        )
    )
        private set

    fun selectAnswer(answer: Int) {
        if (!uiState.value.answerSubmitted) {
            uiState.value = uiState.value.copy(
                selectedAnswer = answer
            )
        }
    }

    fun submitAnswer() {
        if (uiState.value.selectedAnswer != null) {
            uiState.value = uiState.value.copy(
                answerSubmitted = true
            )
        }
    }

    fun nextQuestion() {
        val state = uiState.value

        if (state.currentQuestionIndex >= state.questions.lastIndex) {
            uiState.value = state.copy(
                practiceFinished = true
            )
        } else {
            uiState.value = state.copy(
                currentQuestionIndex = state.currentQuestionIndex + 1,
                selectedAnswer = null,
                answerSubmitted = false
            )
        }
    }

    private fun generatePracticeQuestions(): List<Question> {

        val multiplicationA = (2..12).random()
        val multiplicationB = (2..12).random()
        val multiplicationAnswer =
            multiplicationA * multiplicationB

        val divisionAnswer = (2..12).random()
        val divisor = (2..12).random()
        val dividend = divisionAnswer * divisor

        val multiplicationQuestion = Question(
            text = "$multiplicationA × $multiplicationB",
            correctAnswer = multiplicationAnswer,
            options = generateOptions(multiplicationAnswer),
            type = QuestionType.MULTIPLICATION
        )

        val divisionQuestion = Question(
            text = "$dividend ÷ $divisor",
            correctAnswer = divisionAnswer,
            options = generateOptions(divisionAnswer),
            type = QuestionType.DIVISION
        )

        return listOf(
            multiplicationQuestion,
            divisionQuestion
        ).shuffled()
    }

    private fun generateOptions(
        correctAnswer: Int
    ): List<Int> {

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