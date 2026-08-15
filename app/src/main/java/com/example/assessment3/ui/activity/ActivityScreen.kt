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

@Composable
fun ActivityScreen(viewModel: ActivityViewModel = viewModel()) {
    val state = viewModel.uiState.value
    if (state.quizFinished) {
        QuizResultScreen(state)
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
fun QuizResultScreen(state: ActivityUiState) {
    val totalQuestions = state.questions.size
    val overallAccuracy =
        if (totalQuestions == 0) 0
        else (state.totalCorrect * 100) / totalQuestions
    val multiplicationAccuracy = if (totalQuestions == 0) 0 else (state.multiplicationCorrect * 100) / 5
    val divisionAccuracy = if (totalQuestions == 0) 0 else (state.divisionCorrect * 100) / 5
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Practice Complete!", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "${state.totalCorrect} / $totalQuestions",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "$overallAccuracy% Overall Accuracy", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Session Breakdown", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Multiplication: ${state.multiplicationCorrect}/5 ($multiplicationAccuracy%)")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Division: ${state.divisionCorrect}/5 ($divisionAccuracy%)")
            }
        }
    }
}
