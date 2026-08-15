package com.example.assessment3.ui.activity

import androidx.lifecycle.ViewModel
import com.example.assessment3.domain.logic.QuestionGenerator
import com.example.assessment3.domain.logic.ScoreCalculator
import com.example.assessment3.domain.model.Question
import com.example.assessment3.domain.model.QuestionType
import com.example.assessment3.domain.model.QuizResult

data class ActivityUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val totalCorrect: Int = 0,
    val multiplicationCorrect: Int = 0,
    val divisionCorrect: Int = 0,
    val selectedAnswer: Int? = null,
    val answerSubmitted: Boolean = false,
    val quizFinished: Boolean = false
)
{
    val currentQuestion: Question?
        get() = questions.getOrNull(currentQuestionIndex)
}

class ActivityViewModel : ViewModel()
{
    var uiState = androidx.compose.runtime.mutableStateOf(
        ActivityUiState(
            questions = QuestionGenerator.generateSession()
        )
    )
        private set
    fun selectAnswer(answer: Int)
    {
        if (!uiState.value.answerSubmitted)
        {
            uiState.value = uiState.value.copy(
                selectedAnswer = answer
            )
        }
    }
    fun submitAnswer()
    {
        val state = uiState.value
        val question = state.currentQuestion ?: return
        val selectedAnswer = state.selectedAnswer ?: return
        val isCorrect = selectedAnswer == question.correctAnswer
        uiState.value = state.copy(
            totalCorrect = state.totalCorrect + if (isCorrect) 1 else 0,
            multiplicationCorrect =
                state.multiplicationCorrect +
                        if (isCorrect && question.type == QuestionType.MULTIPLICATION) 1 else 0,
            divisionCorrect =
                state.divisionCorrect +
                        if (isCorrect && question.type == QuestionType.DIVISION) 1 else 0,
            answerSubmitted = true
        )
    }
    fun nextQuestion()
    {
        val state = uiState.value
        if (state.currentQuestionIndex >= state.questions.lastIndex)
        {
            uiState.value = state.copy(
                quizFinished = true
            )
        }
        else
        {
            uiState.value = state.copy(
                currentQuestionIndex = state.currentQuestionIndex + 1,
                selectedAnswer = null,
                answerSubmitted = false
            )
        }
    }
    fun getQuizResult(): QuizResult {
        val state = uiState.value
        return ScoreCalculator.calculate(
            totalCorrect = state.totalCorrect,
            totalQuestions = state.totalCorrect,
            multiplicationCorrect = state.questions.size,
            divisionCorrect = state.divisionCorrect
        )
    }
}