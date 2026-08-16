package com.example.assessment3.ui.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assessment3.data.repository.QuizRepository
import com.example.assessment3.data.preferences.SettingsRepository

@Composable
fun LandingScreen(
    quizRepository: QuizRepository,
    settingsRepository: SettingsRepository,
    onOpenStatistics: () -> Unit,
    onOpenSettings: () -> Unit,
    onStartActivity: () -> Unit,
    onOpenPractice: () -> Unit
) {
    val viewModel: LandingViewModel = viewModel(
        factory = LandingViewModelFactory(
            quizRepository = quizRepository,
            settingsRepository = settingsRepository
        )
    )
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

        Column(modifier = Modifier.fillMaxWidth().padding(24.dp))
        {
            Text( text = "Maths Practice", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text( text = "Build your number skills", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(28.dp))
            Text( text = "Your Progress", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp))
            {
                StatisticCard(
                    value = "${state.overallAccuracy}%",
                    label = "Accuracy",
                    modifier = Modifier.weight(1f)
                )
                StatisticCard(
                    value = state.sessionsCompleted.toString(),
                    label = "Sessions",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
            {
                Text(text = "×  ÷", fontSize = 38.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${state.difficulty} Quiz", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text( text = "Ready to test your maths skills?",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center)
                Text(
                    text = "5 Multiplication - 5 Division",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "10 Questions",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onStartActivity,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Start Quiz") }
            }
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = "Want a quick warm-up?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Try 2 practice questions without affecting your score.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Button(
                    onClick = onOpenPractice,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Quick Practice")
                }
        }
    }
}

@Composable
private fun StatisticCard( value: String, label: String, modifier: Modifier = Modifier)
{
    Card( modifier = modifier, shape = RoundedCornerShape(16.dp))
    {
        Column( modifier = Modifier.fillMaxWidth().padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text( text = value, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text( text = label, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}