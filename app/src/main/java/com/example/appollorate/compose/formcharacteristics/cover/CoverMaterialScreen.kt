package com.example.appollorate.compose.formcharacteristics.cover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appollorate.R
import com.example.appollorate.compose.inventoryfield.InventoryField
import com.example.appollorate.compose.inventoryfield.InventoryFieldViewModel
import com.example.appollorate.compose.utils.AppolloRateNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoverMaterialScreen(
    navigationType: AppolloRateNavigationType,
    coverMaterialScreenViewModel: CoverMaterialScreenViewModel = viewModel(factory = CoverMaterialScreenViewModel.Factory),
    inventoryFieldViewModel: InventoryFieldViewModel = viewModel(factory = InventoryFieldViewModel.Factory),
    navigateUp: () -> Unit,
) {
    val inventoryFieldListState by coverMaterialScreenViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            state = lazyListState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
                Modifier.fillMaxWidth().padding(16.dp)
            } else {
                Modifier.fillMaxWidth().padding(40.dp)
            },
        ) {
            items(inventoryFieldListState.inventoryFieldList, key = { i -> i.id }) {
                InventoryField(inventoryField = it)
            }
            item {
                ElevatedButton(
                    onClick = {
                        inventoryFieldViewModel.sendInput()
                        navigateUp()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Text(text = stringResource(R.string.NEXT))
                }
            }
        }
    }
}
