package com.example.appollorate.compose.identification

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appollorate.compose.inventory.InventoryField
import com.example.appollorate.ui.theme.AppollorateTheme
import com.example.appollorate.viewmodels.InventoryFieldViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentificationScreen(
    inventoryFieldViewModel: InventoryFieldViewModel = viewModel(factory = InventoryFieldViewModel.Factory),
) {
    val inventoryFieldListState by inventoryFieldViewModel.uiListState.collectAsState()
    /*Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Titel") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Auteur") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Jaartal") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            ElevatedButton(
                onClick = { *//*TODO*//* },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(text = stringResource(R.string.NEXT))
            }
        }
    }*/
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
    ) {
        items(inventoryFieldListState.inventoryFieldList) {
            InventoryField()
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
