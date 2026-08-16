package com.example.assessment3.ui.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assessment3.data.repository.QuizRepository
import com.example.assessment3.data.preferences.SettingsRepository
import com.example.assessment3.ui.activity.ActivityScreen
import com.example.assessment3.ui.landing.LandingScreen
import com.example.assessment3.ui.statistics.StatisticsScreen
import com.example.assessment3.Assessment3Application
import com.example.assessment3.data.repository.MathFactRepository
import com.example.assessment3.ui.settings.SettingsScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.assessment3.ui.practice.PracticeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    quizRepository: QuizRepository,
    settingsRepository: SettingsRepository,
    mathFactRepository: MathFactRepository
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = currentRoute == Routes.LANDING,
                    onClick = {
                        navController.navigate(Routes.LANDING)
                    },
                    icon = {
                        Text("🏠")
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = currentRoute == Routes.ACTIVITY,
                    onClick = {
                        navController.navigate(Routes.ACTIVITY)
                    },
                    icon = {
                        Text("✏️")
                    },
                    label = {
                        Text("Activity")
                    }
                )

                NavigationBarItem(
                    selected = currentRoute == Routes.STATISTICS,
                    onClick = {
                        navController.navigate(Routes.STATISTICS)
                    },
                    icon = {
                        Text("📊")
                    },
                    label = {
                        Text("Progress")
                    }
                )

                NavigationBarItem(
                    selected = currentRoute == Routes.SETTINGS,
                    onClick = {
                        navController.navigate(Routes.SETTINGS)
                    },
                    icon = {
                        Text("⚙️")
                    },
                    label = {
                        Text("Settings")
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.LANDING
        ) {
            composable(Routes.LANDING) {
                LandingScreen(
                    quizRepository = quizRepository,
                    onStartActivity = { navController.navigate(Routes.ACTIVITY) },
                    onOpenStatistics = { navController.navigate(Routes.STATISTICS) },
                    onOpenSettings = { navController.navigate(Routes.SETTINGS)}
                )
            }
            composable(Routes.ACTIVITY) { ActivityScreen(quizRepository = quizRepository,
                settingsRepository = settingsRepository,
                mathFactRepository = mathFactRepository,
                onBackHome = {
                    navController.navigate(Routes.LANDING) {
                        popUpTo(Routes.LANDING) {
                            inclusive = false
                        }
                    }
                },
                onOpenStatistics = {
                    navController.navigate(Routes.STATISTICS)
                }) }
            composable(Routes.STATISTICS) { StatisticsScreen(quizRepository = quizRepository) }
            composable(Routes.SETTINGS) { SettingsScreen(settingsRepository = settingsRepository) }
            composable(Routes.PRACTICE) {
                PracticeScreen(
                    onBackHome = {
                        navController.navigate(Routes.LANDING)
                    }
                )
            }
        }
    }

}