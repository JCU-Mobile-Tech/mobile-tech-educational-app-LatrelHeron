package com.example.assessment3.ui.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LandingScreen(
    onStartActivity: () -> Unit,
    onOpenStatistics: () -> Unit,
    onOpenSettings: () -> Unit
) {
    Column {
        Button(onClick = onStartActivity) {
            Text("Start Activity")
        }

        Button(onClick = onOpenStatistics) {
            Text("Statistics")
        }

        Button(onClick = onOpenSettings) {
            Text("Settings")
        }
    }
}