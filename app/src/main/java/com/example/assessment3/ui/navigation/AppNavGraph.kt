package com.example.assessment3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assessment3.ui.activity.ActivityScreen
import com.example.assessment3.ui.landing.LandingScreen
import com.example.assessment3.ui.statistics.StatisticsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LANDING
    ) {
        composable(Routes.LANDING) {
            LandingScreen(
                onStartActivity = { navController.navigate(Routes.ACTIVITY) },
                onOpenStatistics = { navController.navigate(Routes.STATISTICS) },
                onOpenSettings = { navController.navigate(Routes.SETTINGS)}
            )
        }
        composable(Routes.ACTIVITY) { ActivityScreen() }
        composable(Routes.STATISTICS) { StatisticsScreen() }
        composable(Routes.SETTINGS) { StatisticsScreen() }
    }
}