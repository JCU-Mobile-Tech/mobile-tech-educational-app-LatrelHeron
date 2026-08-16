package com.example.assessment3.ui.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assessment3.data.repository.QuizRepository
import com.example.assessment3.domain.model.QuizResult
import com.example.assessment3.data.repository.MathCheckRepository
import com.example.assessment3.data.preferences.SettingsRepository
@Composable
fun ActivityScreen(
    quizRepository: QuizRepository,
    settingsRepository: SettingsRepository,
    mathCheckRepository: MathCheckRepository,
    onBackHome: () -> Unit,
    onOpenStatistics: () -> Unit
) {
    val viewModel: ActivityViewModel = viewModel(
        factory = ActivityViewModelFactory(
            quizRepository = quizRepository,
            settingsRepository = settingsRepository,
            mathCheckRepository = mathCheckRepository
        )
    )
    val state = viewModel.uiState.value
    if (state.quizFinished) {
        QuizResultScreen(
            result = viewModel.getQuizResult(),
            state = state,
            onBackHome = onBackHome,
            onOpenStatistics = onOpenStatistics
            )
        return
    }
    val question = state.currentQuestion ?: return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mixed Practice", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Question ${state.currentQuestionIndex + 1} of ${state.questions.size}")
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { (state.currentQuestionIndex + 1).toFloat() / state.questions.size },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = question.text, fontSize = 44.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(40.dp))

        question.options.chunked(2).forEach { rowOptions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowOptions.forEach { answer ->
                    OutlinedButton(
                        onClick = { viewModel.selectAnswer(answer) },
                        modifier = Modifier.weight(1f),
                        enabled = !state.answerSubmitted
                    ) {
                        Text(text = answer.toString(), fontSize = 20.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (state.answerSubmitted) {
            Text(
                text = if (state.selectedAnswer == question.correctAnswer) {
                    "Correct!"
                } else {
                    "Correct answer: ${question.correctAnswer}"
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.nextQuestion() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (state.currentQuestionIndex == state.questions.lastIndex) {
                        "View Results"
                    } else {
                        "Next Question"
                    }
                )
            }
        } else {
            Button(
                onClick = { viewModel.submitAnswer() },
                enabled = state.selectedAnswer != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Answer")
            }
        }
    }
}

@Composable
private fun QuizResultScreen(
    result: QuizResult,
    state: ActivityUiState,
    onBackHome: () -> Unit,
    onOpenStatistics: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Practice Complete!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${result.totalCorrect} / ${result.totalQuestions}",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "${result.overallAccuracy}% Overall Accuracy",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Session Breakdown",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Multiplication: ${result.multiplicationCorrect}/5" +
                            "(${result.multiplicationAccuracy}%)"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Division: ${result.divisionCorrect}/5" +
                            "(${result.divisionAccuracy}%)"
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Did you know?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                when {
                    state.isFactLoading -> {
                        Text("Loading maths fact...")
                    }

                    state.factError -> {
                        Text(
                            "Maths fact unavailable. Check your internet connection."
                        )
                    }

                    state.mathFact.isNotBlank() -> {
                        Text(state.mathFact)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onOpenStatistics,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("View Statistics")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onBackHome,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Back Home")
                }
            }
        }
    }
}

