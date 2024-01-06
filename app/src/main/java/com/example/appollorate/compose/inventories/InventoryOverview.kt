package com.example.appollorate.compose.inventories

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appollorate.compose.utils.AppolloRateNavigationType

@Composable
fun InventoryOverview(
    navigationType: AppolloRateNavigationType,
    inventoryOverviewViewModel: InventoryOverviewViewModel = viewModel(factory = InventoryOverviewViewModel.Factory),
) {
    val inventoryScreenState by inventoryOverviewViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
            Modifier.fillMaxWidth().padding(16.dp)
        } else {
            Modifier.fillMaxWidth().padding(40.dp)
        },
    ) {
        items(inventoryScreenState.inventoryList) {
            Text(text = it.title)
        }
    }
}
