package com.example.appollorate.compose.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appollorate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppolloRateBottomAppBar(
    goHome: () -> Unit,
    goToStartScreen: () -> Unit,
    goToInventories: () -> Unit,
    logOut: () -> Unit,
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        actions = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = goHome,
                        modifier = Modifier.size(110.dp),
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Filled.Home, contentDescription = "navigate to home screen")
                            Text(
                                text = "Homepage",
                                fontSize = 13.sp,
                            )
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = goToStartScreen,
                        modifier = Modifier.size(110.dp),
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Filled.Assignment,
                                contentDescription = "navigate to inventory screen",
                            )
                            Text(
                                text = stringResource(R.string.DAMAGE_REGISTRATION),
                                fontSize = 13.sp,
                            )
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = goToInventories,
                        modifier = Modifier.size(110.dp),
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Filled.ListAlt,
                                contentDescription = "navigate to overview of inventories",
                            )
                            Text(
                                text = stringResource(R.string.INVENTORIES),
                                fontSize = 13.sp,
                            )
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = logOut,
                        modifier = Modifier.size(110.dp),
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Filled.Logout,
                                contentDescription = "log out",
                            )
                            Text(
                                text = "Log out",
                                fontSize = 13.sp,
                            )
                        }
                    }
                }
            }
        },
    )
}
