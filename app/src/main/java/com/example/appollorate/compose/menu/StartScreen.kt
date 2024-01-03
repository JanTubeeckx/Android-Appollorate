package com.example.appollorate.compose.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appollorate.R
import com.example.appollorate.compose.utils.AppolloRateNavigationType
import com.example.appollorate.navigation.NavigationOverview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    startScreenViewModel: StartScreenViewModel = viewModel(factory = StartScreenViewModel.Factory),
    navController: NavController,
    navigationType: AppolloRateNavigationType,
) {
    val startScreenState by startScreenViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    val IDENTIFICATION = stringResource(R.string.IDENTIFICATION)
    val PROTECTION = stringResource(R.string.PROTECTION)
    val SHAPE_AND_DAMAGE = stringResource(R.string.SHAPE_AND_DAMAGE)
    val IDENTIFICATION_ICON = stringResource(R.string.IDENTIFICATION_ICON)
    val PROTECTION_ICON = stringResource(R.string.PROTECTION_ICON)

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
            Modifier.fillMaxHeight().padding(20.dp)
        } else {
            Modifier.fillMaxHeight().padding(30.dp)
        },
    ) {
        items(startScreenState.identificationInventorySteps, key = { s -> s.id }) {
            ElevatedCard(
                onClick = {
                    when (it.description) {
                        IDENTIFICATION -> navController.navigate("Identification/${it.id}")
                        PROTECTION -> navController.navigate("Protection/${it.id}")
                        SHAPE_AND_DAMAGE -> navController.navigate(NavigationOverview.ShapeAndDamage.name)
                    }
                },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.surfaceTint,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(R.dimen.default_elevation),
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
                modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
                    Modifier.fillMaxSize().height(155.dp)
                } else {
                    Modifier.fillMaxSize().height(220.dp)
                },
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                ) {
                    Icon(
                        painter = painterResource(
                            id =
                            when (it.icon) {
                                IDENTIFICATION_ICON -> R.drawable.address_card
                                PROTECTION_ICON -> R.drawable.box
                                else -> R.drawable.file_pen
                            },
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(53.dp),
                    )
                    Text(
                        text = it.description,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}
