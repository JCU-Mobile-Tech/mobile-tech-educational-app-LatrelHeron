package com.example.assessment3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assessment3.data.repository.QuizRepository
import com.example.assessment3.ui.activity.ActivityScreen
import com.example.assessment3.ui.landing.LandingScreen
import com.example.assessment3.ui.statistics.StatisticsScreen
import com.example.assessment3.Assessment3Application
import com.example.assessment3.ui.settings.SettingsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    quizRepository: QuizRepository
) {
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
        composable(Routes.ACTIVITY) { ActivityScreen(quizRepository = quizRepository) }
        composable(Routes.STATISTICS) { StatisticsScreen(quizRepository = quizRepository) }
        composable(Routes.SETTINGS) { SettingsScreen() }
    }
}