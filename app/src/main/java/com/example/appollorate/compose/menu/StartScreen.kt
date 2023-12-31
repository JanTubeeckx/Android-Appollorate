package com.example.appollorate.compose.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    startScreenViewModel: StartScreenViewModel = viewModel(factory = StartScreenViewModel.Factory),
    navController: NavController,
) {
    val startScreenState by startScreenViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    val IDENTIFICATION = stringResource(R.string.IDENTIFICATION)
    val PROTECTION = stringResource(R.string.PROTECTION)

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        items(startScreenState.identificationInventorySteps, key = { s -> s.id }) {
            ElevatedCard(
                onClick = {
                    when (it.description) {
                        IDENTIFICATION -> navController.navigate("Inventory/${it.id}")
                        PROTECTION -> navController.navigate("Protection/${it.id}")
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
                modifier = Modifier
                    .size(width = 370.dp, height = 155.dp),
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
                                "address-card" -> R.drawable.address_card
                                "box" -> R.drawable.box
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
