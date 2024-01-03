package com.example.appollorate.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appollorate.R
import com.example.appollorate.compose.login.LoginApiState
import com.example.appollorate.compose.login.LoginScreen
import com.example.appollorate.compose.login.LoginViewModel
import com.example.appollorate.compose.utils.AppolloRateNavigationType
import com.example.appollorate.ui.theme.AppollorateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigationType: AppolloRateNavigationType,
    goToStartScreen: () -> Unit,
    goToInventories: () -> Unit,
    loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
) {
    var showLogin by remember { mutableStateOf(true) }

    if (loginViewModel.loginApiState != LoginApiState.Success && showLogin) {
        LoginScreen(
            onDismissRequest = { if (loginViewModel.loginApiState != LoginApiState.Success) { { showLogin = false } } },

        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
                Modifier.padding(vertical = 42.dp, horizontal = 5.dp)
            } else {
                Modifier.padding(vertical = 32.dp, horizontal = 5.dp)
            },
        ) {
            Image(
                painter = painterResource(R.drawable.book_open_solid),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp),
            )
            Text(
                text = stringResource(R.string.APP_NAME),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
            )
        }
        ElevatedCard(
            onClick = goToStartScreen,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(R.dimen.default_elevation),
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
            modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
                Modifier.size(width = 320.dp, height = 240.dp)
            } else {
                Modifier.size(width = 620.dp, height = 320.dp)
            },

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Assignment,
                    contentDescription = null,
                    modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
                        Modifier.fillMaxWidth().size(75.dp)
                    } else {
                        Modifier.fillMaxWidth().size(95.dp)
                    },
                )
                Text(
                    text = stringResource(R.string.DAMAGE_REGISTRATION),
                    fontSize = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) 30.sp else 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            ElevatedCard(
                onClick = goToInventories,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(R.dimen.default_elevation),
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
                modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
                    Modifier.size(width = 152.dp, height = 120.dp)
                } else {
                    Modifier.size(width = 302.dp, height = 150.dp)
                },
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(55.dp),
                    )
                    Text(
                        text = stringResource(R.string.INVENTORIES),
                        fontSize = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) 16.sp else 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            ElevatedCard(
                onClick = { /*TODO*/ },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(R.dimen.default_elevation),
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
                modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
                    Modifier.size(width = 152.dp, height = 120.dp)
                } else {
                    Modifier.size(width = 302.dp, height = 150.dp)
                },
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(55.dp)
                            .padding(5.dp),
                    )
                    Text(
                        text = stringResource(R.string.APP_NAME),
                        fontSize = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) 16.sp else 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(46.dp))
        if (navigationType == AppolloRateNavigationType.PERMANENT_NAVIGATION_DRAWER) {
            Text(
                text = "De online inventarisatietool voor boeken",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
            )
        } else {
            Text(
                text = "De inventarisatietool",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "voor boeken.",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppollorateTheme {
        HomeScreen(navigationType = AppolloRateNavigationType.BOTTOM_NAVIGATION, goToStartScreen = {}, goToInventories = {})
    }
}
