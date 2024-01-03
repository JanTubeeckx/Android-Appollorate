package com.example.appollorate.compose.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import com.example.appollorate.navigation.NavigationOverview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
    selectedDestination: NavDestination?,
    onTabPressed: ((String) -> Unit),
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier.padding(top = 70.dp),
    ) {
        NavigationDrawerItem(
            selected = selectedDestination?.route == NavigationOverview.Start.name,
            label = {
                Text(
                    text = "Home",
                    fontSize = 15.sp,
                    // modifier = Modifier.padding(horizontal = 8.dp),
                )
            },
            icon = {
                Icon(
                    imageVector = NavigationOverview.Start.icon,
                    contentDescription = NavigationOverview.Start.name,
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
            ),
            onClick = { onTabPressed(NavigationOverview.Start.name) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        NavigationDrawerItem(
            selected = selectedDestination?.route == NavigationOverview.StartScreen.name,
            label = {
                Text(
                    text = "Start",
                    fontSize = 15.sp,
                    // modifier = Modifier.padding(horizontal = 8.dp),
                )
            },
            icon = {
                Icon(
                    imageVector = NavigationOverview.StartScreen.icon,
                    contentDescription = NavigationOverview.StartScreen.name,
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
            ),
            onClick = { onTabPressed(NavigationOverview.StartScreen.name) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        NavigationDrawerItem(
            selected = selectedDestination?.route == NavigationOverview.Inventories.name,
            label = {
                Text(
                    text = "Overzicht",
                    fontSize = 15.sp,
                    // modifier = Modifier.padding(horizontal = 8.dp),
                )
            },
            icon = {
                Icon(
                    imageVector = NavigationOverview.Inventories.icon,
                    contentDescription = NavigationOverview.Inventories.name,
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
            ),
            onClick = { onTabPressed(NavigationOverview.Inventories.name) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        NavigationDrawerItem(
            selected = false,
            label = {
                Text(
                    text = "Log uit",
                    fontSize = 15.sp,
                    // modifier = Modifier.padding(horizontal = 8.dp),
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = NavigationOverview.Start.name,
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
            ),
            onClick = { onTabPressed(NavigationOverview.Start.name) },
        )
    }
}
