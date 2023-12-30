package com.example.appollorate.compose.inventoryfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appollorate.R
import com.example.appollorate.data.model.InventoryField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryField(
    inventoryField: InventoryField,
    inventoryFieldViewModel: InventoryFieldViewModel = viewModel(factory = InventoryFieldViewModel.Factory),
) {
    val inventoryFieldState by inventoryFieldViewModel.uiState.collectAsState()

    when (inventoryField.type) {
        "text" -> {
            OutlinedTextField(
                value = inventoryFieldState.input[inventoryField.id] ?: "",
                onValueChange = { inventoryFieldViewModel.setInput(inventoryField.id, it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = inventoryField.description) },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        "select" ->
            ExposedDropdownMenuBox(
                expanded = inventoryFieldState.showDropDown,
                onExpandedChange = { inventoryFieldViewModel.showDropDown() },
            ) {
                OutlinedTextField(
                    value = "test",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = inventoryFieldState.showDropDown) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    label = { Text(stringResource(R.string.APP_NAME)) },
                )
                ExposedDropdownMenu(
                    expanded = inventoryFieldState.showDropDown,
                    onDismissRequest = { inventoryFieldViewModel.showDropDown() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.onPrimary),
                ) {
/*                    formulas.forEach { formula ->
                        DropdownMenuItem(
                            text = { Text(formula.name) },
                            onClick = {
                                selectedFormula = formula
                                reservation?.formula = formula
                                reservationDetailViewModel.setReservation(reservation!!)
                                reservationDetailViewModel.dismissDropDown()
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }*/
                }
            }
        "number" -> TextField(value = "test", onValueChange = {})
        "radio" -> TextField(value = "test", onValueChange = {})
    }
}
