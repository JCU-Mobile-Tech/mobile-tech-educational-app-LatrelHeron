package com.example.assessment3.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assessment3.data.preferences.SettingsRepository
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(settingsRepository: SettingsRepository
) {
    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(
            settingsRepository = settingsRepository
        )
    )

    val state = viewModel.uiState
        .collectAsStateWithLifecycle()
        .value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Text(
            text = "Settings",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Sound",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Answer feedback sounds",
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Switch(
                checked = state.soundEnabled,
                onCheckedChange = {
                    viewModel.setSoundEnabled(it)
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Difficulty",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Choose the number range used in practice questions.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        DifficultyOption(
            label = "Easy",
            description = "Numbers 2 to 5",
            selected = state.difficulty == "Easy",
            onClick = {
                viewModel.setDifficulty("Easy")
            }
        )

        DifficultyOption(
            label = "Normal",
            description = "Numbers 2 to 12",
            selected = state.difficulty == "Normal",
            onClick = {
                viewModel.setDifficulty("Normal")
            }
        )

        DifficultyOption(
            label = "Hard",
            description = "Numbers 2 to 20",
            selected = state.difficulty == "Hard",
            onClick = {
                viewModel.setDifficulty("Hard")
            }
        )
    }
}
@Composable
private fun DifficultyOption(
    label: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {

            Column(
                modifier = Modifier.padding(vertical = 4.dp)
            ) {

                Text(
                    text = label,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = description,
                    fontSize = 12.sp
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}