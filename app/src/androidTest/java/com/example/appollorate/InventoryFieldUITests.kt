package com.example.appollorate

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appollorate.compose.AppolloRateApp
import com.example.appollorate.compose.utils.AppolloRateNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InventoryFieldUITests {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppolloRateApp(navController = navController, navigationType = AppolloRateNavigationType.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun verifyHomeScreen() {
        composeTestRule
            .onNodeWithText("Schaderegistratie")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToStartScreen() {
        composeTestRule
            .onNodeWithText("Schaderegistratie")
            .performClick()
        composeTestRule
            .onNodeWithText("Identificatie")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToIdentificationScreen() {
        composeTestRule
            .onNodeWithText("Schaderegistratie")
            .performClick()
        composeTestRule
            .onNodeWithText("Identificatie")
            .performClick()
    }
}
