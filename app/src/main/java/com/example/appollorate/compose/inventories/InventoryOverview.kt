package com.example.appollorate.compose.inventories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.appollorate.R
import com.example.appollorate.compose.utils.AppolloRateNavigationType

@Composable
fun InventoryOverview(
    navigationType: AppolloRateNavigationType,
    inventoryOverviewViewModel: InventoryOverviewViewModel = viewModel(factory = InventoryOverviewViewModel.Factory),
) {
    val inventoryScreenState by inventoryOverviewViewModel.uiState.collectAsState()

    LazyVerticalGrid(
        columns = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
            GridCells.Adaptive(minSize = 150.dp)
        } else {
            GridCells.Adaptive(minSize = 200.dp)
        },
        modifier = Modifier.padding(24.dp),
    ) {
        items(inventoryScreenState.inventoryList) {
            ElevatedCard(
                shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
                modifier = Modifier.padding(8.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    AsyncImage(
                        model = it.imagePath,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize(),
                    )
                    Text(
                        text = it.title,
                        fontSize = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) 16.sp else 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                }
            }
        }
    }
}
