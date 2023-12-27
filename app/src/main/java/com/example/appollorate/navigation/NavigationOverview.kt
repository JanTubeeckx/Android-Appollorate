package com.example.appollorate.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.appollorate.R

enum class NavigationOverview(@StringRes val title: Int, val icon: ImageVector) {
    Start(title = R.string.APP_NAME, icon = Icons.Filled.Home),
    StartScreen(title = R.string.DAMAGE_REGISTRATION, icon = Icons.Filled.Assignment),
    Identification(title = R.string.IDENTIFICATION, icon = Icons.Filled.AccountBox),
    Inventories(title = R.string.INVENTORIES, icon = Icons.Filled.List),
}
