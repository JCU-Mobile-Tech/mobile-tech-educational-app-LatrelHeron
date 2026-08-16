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
import com.example.assessment3.data.repository.MathCheckRepository
import com.example.assessment3.ui.settings.SettingsScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.assessment3.ui.practice.PracticeScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assessment3.ui.statistics.StatisticsViewModel
import com.example.assessment3.ui.statistics.StatisticsViewModelFactory
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
@Composable
fun AppNavGraph(
    navController: NavHostController,
    quizRepository: QuizRepository,
    settingsRepository: SettingsRepository,
    mathCheckRepository: MathCheckRepository
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route
    val statisticsViewModel: StatisticsViewModel = viewModel(
        factory = StatisticsViewModelFactory(
            quizRepository = quizRepository
        )
    )

    val rankState = statisticsViewModel.uiState
        .collectAsStateWithLifecycle()
        .value

    val rankColor = when (rankState.currentRank) {
        "Bronze" -> Color(0xFFCD7F32)
        "Silver" -> Color(0xFFC0C0C0)
        "Gold" -> Color(0xFFFFD700)
        "Diamond" -> Color(0xFF67D5E8)
        "Netherite" -> Color(0xFF4B3758)
        else -> Color.Gray
    }
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
                        Icon(
                            imageVector = Icons.Filled.Shield,
                            contentDescription = "${rankState.currentRank} rank",
                            tint = rankColor
                        )
                    },
                    label = {
                        Text("Rank")
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
            startDestination = Routes.LANDING,
            modifier = Modifier.padding(innerPadding)

        ) {
            composable(Routes.LANDING) {
                LandingScreen(
                    quizRepository = quizRepository,
                    settingsRepository = settingsRepository,
                    onStartActivity = { navController.navigate(Routes.ACTIVITY) },
                    onOpenStatistics = { navController.navigate(Routes.STATISTICS) },
                    onOpenSettings = { navController.navigate(Routes.SETTINGS)},
                    onOpenPractice = { navController.navigate(Routes.PRACTICE)}
                )
            }
            composable(Routes.ACTIVITY) { ActivityScreen(quizRepository = quizRepository,
                settingsRepository = settingsRepository,
                mathCheckRepository = mathCheckRepository,
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