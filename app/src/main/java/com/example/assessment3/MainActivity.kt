package com.example.assessment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.assessment3.ui.navigation.AppNavGraph
import com.example.assessment3.ui.theme.Assessment3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assessment3Theme {
                val navController = rememberNavController()
                val app = application as Assessment3Application

                AppNavGraph(
                    navController = navController,
                    quizRepository = app.container.quizRepository,
                    settingsRepository = app.container.settingsRepository,
                    mathCheckRepository = app.container.mathCheckRepository
                )

            }
        }
    }
}

