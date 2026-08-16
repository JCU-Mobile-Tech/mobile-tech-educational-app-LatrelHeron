package com.example.assessment3.ui.activity

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment3.data.preferences.SettingsRepository
import com.example.assessment3.data.repository.QuizRepository
import com.example.assessment3.domain.logic.QuestionGenerator
import com.example.assessment3.domain.logic.ScoreCalculator
import com.example.assessment3.domain.model.Question
import com.example.assessment3.domain.model.QuestionType
import com.example.assessment3.domain.model.QuizResult
import com.example.assessment3.data.repository.MathFactRepository
import kotlinx.coroutines.launch

data class ActivityUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val totalCorrect: Int = 0,
    val multiplicationCorrect: Int = 0,
    val divisionCorrect: Int = 0,
    val selectedAnswer: Int? = null,
    val answerSubmitted: Boolean = false,
    val quizFinished: Boolean = false,
    val sessionSaved: Boolean = false,
    val mathFact: String = "",
    val isFactLoading: Boolean = false,
    val factError: Boolean = false
)
{
    val currentQuestion: Question?
        get() = questions.getOrNull(currentQuestionIndex)
}

class ActivityViewModel(
    private val quizRepository: QuizRepository,
    private val settingsRepository: SettingsRepository,
    private val mathFactRepository: MathFactRepository
) : ViewModel()
{
    var uiState = mutableStateOf(
        ActivityUiState()
    )
        private set
    init {
        loadQuestions()
    }
    private fun loadMathFact(number: Int) {

        uiState.value = uiState.value.copy(
            isFactLoading = true,
            factError = false
        )

        viewModelScope.launch {
            try {
                val fact = mathFactRepository.getMathFact(number)

                uiState.value = uiState.value.copy(
                    mathFact = fact,
                    isFactLoading = false
                )

            } catch (e: Exception) {

                uiState.value = uiState.value.copy(
                    isFactLoading = false,
                    factError = true
                )
            }
        }
    }

    private fun loadQuestions() {
        viewModelScope.launch {

            val difficulty = settingsRepository.difficulty.first()

            uiState.value = uiState.value.copy(
                questions = QuestionGenerator.generateSession(
                    difficulty = difficulty
                )
            )
        }
    }

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
        if (state.currentQuestionIndex >= state.questions.lastIndex) {
            if (!state.sessionSaved) {
                viewModelScope.launch {
                    quizRepository.saveAttempt(
                        totalCorrect = state.totalCorrect,
                        multiplicationCorrect = state.multiplcationCorrect,
                        divisionCorrect = state.divisionCorrect
                    )
                }
            }
            uiState.value = state.copy(
                quizFinished = true,
                sessionSaved = true
            )
            loadMathFact(state.totalCorrect)
        } else {
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
            totalQuestions = state.questions.size,
            multiplicationCorrect = state.multiplicationCorrect,
            divisionCorrect = state.divisionCorrect
        )
    }
}