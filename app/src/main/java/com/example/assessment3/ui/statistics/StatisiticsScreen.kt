package com.example.assessment3.ui.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assessment3.data.repository.QuizRepository
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

private val ProgressGreen = Color(0xFFE3F4E8)
private val SessionsBlue = Color(0xFFE3EEFA)
private val RankPurple = Color(0xFFEDE7F6)
private val StatYellow = Color(0xFFFFF4D8)

private val BronzeColor = Color(0xFFD57F28)
private val HeadingBlue = Color(0xFF3949AB)

@Composable
fun StatisticsScreen(    quizRepository: QuizRepository
) {
    val viewModel: StatisticsViewModel = viewModel(
        factory = StatisticsViewModelFactory(
            quizRepository = quizRepository
        )
    )

    val state = viewModel.uiState
        .collectAsStateWithLifecycle()
        .value

    val rankColor = when (state.currentRank) {
        "Bronze" -> Color(0xFFCD7F32)
        "Silver" -> Color(0xFFC0C0C0)
        "Gold" -> Color(0xFFFFD700)
        "Diamond" -> Color(0xFF67D5E8)
        "Netherite" -> Color(0xFF4B3758)
        else -> Color.Gray
    }

    var showResetDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(24.dp)
    ) {

        Text(
            text = "Rank",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = RankPurple)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Shield,
                    contentDescription = "${state.currentRank} rank",
                    tint = rankColor,
                    modifier = Modifier.size(72.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = state.currentRank,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = rankColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${state.sessionsCompleted} Sessions Completed"
                )

                if (state.sessionsUntilNextRank > 0) {

                    Spacer(modifier = Modifier.height(16.dp))

                    LinearProgressIndicator(
                        progress = {
                            calculateRankProgress(
                                state.sessionsCompleted
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text =
                            "${state.sessionsUntilNextRank} sessions until ${state.nextRank}"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Your Statistics",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            StatCard(
                title = "Overall",
                value = "${state.overallAccuracy}%",
                containerColor = ProgressGreen,
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "Sessions",
                value = state.sessionsCompleted.toString(),
                containerColor = SessionsBlue,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            StatCard(
                title = "Multiplication",
                value = "${state.multiplicationAccuracy}%",
                containerColor = RankPurple,
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "Division",
                value = "${state.divisionAccuracy}%",
                containerColor = StatYellow,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Rank Milestones",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        RankRow("Bronze", 10, state.sessionsCompleted)
        RankRow("Silver", 25, state.sessionsCompleted)
        RankRow("Gold", 50, state.sessionsCompleted)
        RankRow("Diamond", 75, state.sessionsCompleted)
        RankRow("Netherite", 100, state.sessionsCompleted)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                showResetDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset Progress")
        }
    }
    if (showResetDialog) {

        AlertDialog(
            onDismissRequest = {
                showResetDialog = false
            },

            title = {
                Text("Reset Progress?")
            },

            text = {
                Text(
                    "This will permanently delete all completed sessions, accuracy statistics and rank progress."
                )
            },

            confirmButton = {
                Button(
                    onClick = {
                        viewModel.resetProgress()
                        showResetDialog = false
                    }
                ) {
                    Text("Reset")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showResetDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
@Composable
private fun StatCard(
    title: String,
    value: String,
    containerColor: Color,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = value,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun RankRow(
    rank: String,
    requiredSessions: Int,
    sessionsCompleted: Int
) {

    val unlocked =
        sessionsCompleted >= requiredSessions

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = if (unlocked) {
                "✓ $rank"
            } else {
                "○ $rank"
            },
            fontWeight = if (unlocked) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            }
        )

        Text(
            text = "$requiredSessions sessions"
        )
    }
}

private fun calculateRankProgress(
    sessions: Int
): Float {

    return when {
        sessions >= 100 -> 1f

        sessions >= 75 ->
            (sessions - 75) / 25f

        sessions >= 50 ->
            (sessions - 50) / 25f

        sessions >= 25 ->
            (sessions - 25) / 25f

        sessions >= 10 ->
            (sessions - 10) / 15f

        else ->
            sessions / 10f
    }
}