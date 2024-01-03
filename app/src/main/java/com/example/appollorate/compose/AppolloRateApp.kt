package com.example.appollorate.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appollorate.R
import com.example.appollorate.compose.utils.AppolloRateBottomAppBar
import com.example.appollorate.compose.utils.AppolloRateNavigationRail
import com.example.appollorate.compose.utils.AppolloRateNavigationType
import com.example.appollorate.compose.utils.AppolloRateTopAppBar
import com.example.appollorate.compose.utils.NavigationDrawerContent
import com.example.appollorate.navigation.NavComponent
import com.example.appollorate.navigation.NavigationOverview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppolloRateApp(
    navigationType: AppolloRateNavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack = navController.previousBackStackEntry != null
    val navigateUp: () -> Unit = { navController.navigateUp() }

    val goToHome: () -> Unit = { navController.popBackStack(NavigationOverview.Start.name, inclusive = false) }
    val goToStartScreen = { navController.navigate(NavigationOverview.StartScreen.name) { launchSingleTop = true } }
    val goToIdentification = { navController.navigate(NavigationOverview.Identification.name) { launchSingleTop = true } }
    val goToReservations = { navController.navigate(NavigationOverview.Inventories.name) { launchSingleTop = true } }

    val currentScreenTitle = NavigationOverview.valueOf(
        backStackEntry?.destination?.route?.substringBefore("/") ?: NavigationOverview.Start.name,
    ).title

    var showCamera by remember { mutableStateOf(false) }

    if (navigationType == AppolloRateNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(drawerContent = {
            PermanentDrawerSheet(Modifier.width(160.dp)) {
                NavigationDrawerContent(
                    navController = navController,
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp),
                )
            }
        }) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                topBar = {
                    AppolloRateTopAppBar(
                        canNavigateBack = canNavigateBack,
                        navigateUp = navigateUp,
                        currentScreenTitle = currentScreenTitle,
                    )
                },
            ) { innerPadding ->
                NavComponent(
                    navigationType = navigationType,
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                    goToStartScreen = goToStartScreen,
                    navigateUp = navigateUp,
                    showCamera = showCamera,
                    openCamera = { showCamera = true },
                    closeCamera = { showCamera = false },
                )
            }
        }
    } else if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            topBar = {
                if (canNavigateBack) {
                    AppolloRateTopAppBar(
                        canNavigateBack = canNavigateBack,
                        navigateUp = navigateUp,
                        currentScreenTitle = currentScreenTitle,
                    )
                }
            },
            bottomBar = {
                if (canNavigateBack) {
                    AppolloRateBottomAppBar(
                        navController = navController,
                        goHome = goToHome,
                        goToStartScreen = goToStartScreen,
                        goToInventories = goToReservations,
                    )
                }
            },
        ) { innerPadding ->
            NavComponent(
                navigationType = navigationType,
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                goToStartScreen = goToStartScreen,
                navigateUp = navigateUp,
                showCamera = showCamera,
                openCamera = { showCamera = true },
                closeCamera = { showCamera = false },
            )
        }
    } else {
        Row {
            AnimatedVisibility(visible = navigationType == AppolloRateNavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                AppolloRateNavigationRail(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                )
            }
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    AppolloRateTopAppBar(
                        canNavigateBack = canNavigateBack,
                        navigateUp = navigateUp,
                        currentScreenTitle = currentScreenTitle,
                    )
                },
            ) { innerPadding ->
                NavComponent(
                    navigationType = navigationType,
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                    goToStartScreen = goToStartScreen,
                    navigateUp = navigateUp,
                    showCamera = showCamera,
                    openCamera = { showCamera = true },
                    closeCamera = { showCamera = false },
                )
            }
        }
    }
}
