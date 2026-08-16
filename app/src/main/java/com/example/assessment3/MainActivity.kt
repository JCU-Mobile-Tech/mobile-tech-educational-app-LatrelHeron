package com.example.assessment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.assessment3.ui.navigation.AppNavGraph
import com.example.assessment3.ui.theme.Assessment3Theme
import com.example.assessment3.Assessment3Application

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
                    quizRepository = app.controller.quizRepository,
                    settingsRepository = app.container.settingsRepository,
                    mathFactRepository = app.container.mathFactRepository
                )

            }
        }
    }
}

