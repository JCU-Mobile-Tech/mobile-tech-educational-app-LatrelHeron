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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

private val ProgressGreen = Color(0xFFE3F4E8)
private val SessionsBlue = Color(0xFFE3EEFA)
private val QuizPurple = Color(0xFFEDE7F6)

private val QuizBlue = Color(0xFF536DFE)
private val PracticeGreen = Color(0xFF43A047)
private val HeadingBlue = Color(0xFF3949AB)

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

        Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(24.dp))
        {
            Text( text = "Maths Practice", fontSize = 34.sp, fontWeight = FontWeight.Bold)
            Text( text = "Build your number skills", fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(28.dp))
            Text( text = "Your Progress", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp))
            {
                StatisticCard(
                    value = "${state.overallAccuracy}%",
                    label = "Accuracy",
                    containerColor = ProgressGreen,
                    modifier = Modifier.weight(1f)
                )
                StatisticCard(
                    value = state.sessionsCompleted.toString(),
                    label = "Sessions",
                    containerColor = SessionsBlue,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = QuizPurple)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
            {
                Text(text = "×  ÷", fontSize = 44.sp, fontWeight = FontWeight.ExtraBold, color = HeadingBlue)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${state.difficulty} Quiz", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold,     color = HeadingBlue)
                Spacer(modifier = Modifier.height(8.dp))
                Text( text = "Ready to test your maths skills?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center)
                Text(
                    text = "5 Multiplication - 5 Division",
                    fontSize = 16.sp,
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
                    modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = QuizBlue)
                ) { Text("Start Quiz", fontSize = 18.sp, fontWeight = FontWeight.Bold) }
            }
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = "Want a quick warm-up?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PracticeGreen
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Try 2 practice questions without affecting your score.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onOpenPractice,
                    modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = PracticeGreen)
                ) {
                    Text("Quick Practice", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
        }
    }
}

@Composable
private fun StatisticCard( value: String, label: String, containerColor: Color, modifier: Modifier = Modifier)
{
    Card( modifier = modifier, shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = containerColor))
    {
        Column( modifier = Modifier.fillMaxWidth().padding(34.dp), horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text( text = value, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
            Text( text = label, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}