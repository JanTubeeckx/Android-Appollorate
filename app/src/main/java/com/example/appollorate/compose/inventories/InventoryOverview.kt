package com.example.appollorate.compose.inventories

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appollorate.compose.utils.AppolloRateNavigationType

@Composable
fun InventoryOverview(
    navigationType: AppolloRateNavigationType,
) {
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
        // items()
    }
}
