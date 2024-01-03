package com.example.appollorate.compose.formcharacteristics.cover

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appollorate.R
import com.example.appollorate.compose.utils.AppolloRateNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoverFormChars(
    coverFormCharsViewModel: CoverFormCharsViewModel = viewModel(factory = CoverFormCharsViewModel.Factory),
    navController: NavController,
    navigationType: AppolloRateNavigationType,
) {
    val coverFormCharsState by coverFormCharsViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    val COVER_MATERIAL = stringResource(R.string.COVER_MATERIAL)

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
            Modifier.fillMaxHeight().padding(20.dp)
        } else {
            Modifier.fillMaxHeight().padding(30.dp)
        },
    ) {
        println(coverFormCharsState.coverFormCharInventorySteps)
        items(coverFormCharsState.coverFormCharInventorySteps, key = { s -> s.id }) {
            ElevatedCard(
                onClick = {
                    when (it.description) {
                        COVER_MATERIAL -> navController.navigate("CoverMaterial/${it.id}")
                        // PROTECTION -> navController.navigate("Protection/${it.id}")
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
                    Modifier.fillMaxSize().height(113.dp)
                } else {
                    Modifier.fillMaxSize().height(160.dp)
                },
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    AsyncImage(
                        model = "https://csb100320019d6bcc0d.blob.core.windows.net/thumbnails/" + "${it.icon}",
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Text(
                        text = it.description,
                        fontSize = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) 30.sp else 40.sp,
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
