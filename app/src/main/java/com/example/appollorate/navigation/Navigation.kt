package com.example.appollorate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appollorate.compose.formcharacteristics.FormCharMenu
import com.example.appollorate.compose.formcharacteristics.cover.CoverFormChars
import com.example.appollorate.compose.formcharacteristics.cover.CoverMaterialScreen
import com.example.appollorate.compose.home.HomeScreen
import com.example.appollorate.compose.identification.IdentificationScreen
import com.example.appollorate.compose.menu.StartScreen
import com.example.appollorate.compose.protection.ProtectionScreen

@Composable
fun NavComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    goToStartScreen: () -> Unit,
    navigateUp: () -> Unit,
    openCamera: () -> Unit,
    showCamera: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationOverview.Start.name,
        modifier = modifier,
    ) {
        composable(route = NavigationOverview.Start.name) {
            HomeScreen(goToStartScreen = goToStartScreen, goToInventories = {})
        }
        composable(route = NavigationOverview.StartScreen.name) {
            StartScreen(navController = navController)
        }
        composable(
            "Identification/{stepId}",
            arguments = listOf(
                navArgument("stepId") { type = NavType.StringType },
            ),
        ) {
            IdentificationScreen(goToStartScreen = goToStartScreen, openCamera = openCamera, showCamera = showCamera)
        }
        composable(
            "Protection/{stepId}",
            arguments = listOf(
                navArgument("stepId") { type = NavType.StringType },
            ),
        ) {
            ProtectionScreen(goToStartScreen = goToStartScreen)
        }
        composable(route = NavigationOverview.ShapeAndDamage.name) {
            FormCharMenu(navController = navController)
        }
        composable(
            "Cover/{stepId}",
            arguments = listOf(
                navArgument("stepId") { type = NavType.StringType },
            ),
        ) {
            CoverFormChars(navController = navController)
        }
        composable(
            "CoverMaterial/{stepId}",
            arguments = listOf(
                navArgument("stepId") { type = NavType.StringType },
            ),
        ) {
            CoverMaterialScreen(navigateUp = navigateUp)
        }
    }
}
