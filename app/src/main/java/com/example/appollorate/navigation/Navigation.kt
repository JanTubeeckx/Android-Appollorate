package com.example.appollorate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appollorate.compose.home.HomeScreen
import com.example.appollorate.compose.inventory.InventoryScreen
import com.example.appollorate.compose.menu.StartScreen
import com.example.appollorate.compose.protection.ProtectionScreen

@Composable
fun NavComponent(
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
            StartScreen(navController = navController)
        }
        composable(
            "Inventory/{stepId}",
            arguments = listOf(
                navArgument("stepId") { type = NavType.StringType },
            ),
        ) {
            InventoryScreen()
        }
        composable(
            "Protection/{stepId}",
            arguments = listOf(
                navArgument("stepId") { type = NavType.StringType },
            ),
        ) {
            ProtectionScreen()
        }
    }
}
