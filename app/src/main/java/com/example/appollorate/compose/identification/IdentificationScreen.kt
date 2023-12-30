package com.example.appollorate.compose.identification

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appollorate.compose.inventoryfield.InventoryField
import com.example.appollorate.compose.inventoryfield.InventoryFieldViewModel
import com.example.appollorate.ui.theme.AppollorateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentificationScreen(
    inventoryFieldViewModel: InventoryFieldViewModel = viewModel(factory = InventoryFieldViewModel.Factory),
) {
    val inventoryFieldListState by inventoryFieldViewModel.uiListState.collectAsState()
    val lazyListState = rememberLazyListState()

    println(inventoryFieldListState.inventoryFieldList)

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(24.dp),
    ) {
        items(inventoryFieldListState.inventoryFieldList, key = { i -> i.id }) {
            InventoryField(inventoryField = it)
        }
    }
}

@Preview
@Composable
fun IdentificationScreenPreview() {
    AppollorateTheme {
        IdentificationScreen()
    }
}
