package com.example.appollorate.compose.formcharacteristics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
fun FormCharMenu(
    formCharMenuViewModel: FormCharMenuViewModel = viewModel(factory = FormCharMenuViewModel.Factory),
    navController: NavController,
) {
    val formCharMenuState by formCharMenuViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    val BOOK_ICON = stringResource(R.string.BOOK)
    val CONSTRUCTION_ICON = stringResource(R.string.CONSTRUCTION)
    val SIMPLE_BINDING = stringResource(R.string.SIMPLE_BINDING)
    val CARRIER_MATERIAL = stringResource(R.string.CARRIER_MATERIAL)

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        items(formCharMenuState.FormAndDamageInventorySteps, key = { s -> s.id }) {
            ElevatedCard(
                onClick = {
                    when (it.description) {
                       /* IDENTIFICATION -> navController.navigate("Inventory/${it.id}")
                        PROTECTION -> navController.navigate("Protection/${it.id}")*/
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
                    .size(width = 370.dp, height = 113.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                ) {
                    Icon(
                        painter = painterResource(
                            id = when (it.icon) {
                                BOOK_ICON -> R.drawable.book
                                CONSTRUCTION_ICON -> R.drawable.screwdriver_wrench
                                SIMPLE_BINDING -> R.drawable.paperclip
                                else -> R.drawable.layer_group
                            },
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(40.dp),
                    )
                    Text(
                        text = it.description,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
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
