package com.example.appollorate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appollorate.compose.home.HomeScreen
import com.example.appollorate.compose.menu.StartScreen

@Composable
fun navComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationOverview.Start.name,
        modifier = modifier,
    ) {
        composable(route = NavigationOverview.Start.name) {
            HomeScreen(goToStartScreen = { navController.navigate(NavigationOverview.StartScreen.name) }, goToInventories = {})
        }
        composable(route = NavigationOverview.StartScreen.name) {
            StartScreen()
        }
        composable(route = NavigationOverview.Identification.name) {
            StartScreen()
        }
    }
}
