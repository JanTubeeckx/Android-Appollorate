package com.example.appollorate.compose.inventoryfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
        "select" -> {
            ExposedDropdownMenuBox(
                expanded = inventoryFieldState.showDropDown[inventoryField.id] ?: false,
                onExpandedChange = { inventoryFieldViewModel.showDropDown(inventoryField.id) },
            ) {
                OutlinedTextField(
                    value = inventoryFieldState.dropDownValue[inventoryField.id] ?: "",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = inventoryFieldState.showDropDown[inventoryField.id] ?: false) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    label = { Text(inventoryField.description) },
                )
                ExposedDropdownMenu(
                    expanded = inventoryFieldState.showDropDown[inventoryField.id] ?: false,
                    onDismissRequest = { inventoryFieldViewModel.showDropDown(inventoryField.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.onPrimary),
                ) {
                    inventoryField.dropDownValues?.forEach { dropdownValue ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = dropdownValue.description,
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            },
                            onClick = {
                                inventoryFieldViewModel.setInput(inventoryField.id, dropdownValue.description)
                                inventoryFieldViewModel.hideDropDown(inventoryField.id, dropdownValue.description)
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        "number" -> {
            OutlinedTextField(
                value = inventoryFieldState.input[inventoryField.id] ?: "",
                onValueChange = { inventoryFieldViewModel.setInput(inventoryField.id, it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = inventoryField.description) },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        "radio" -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Is het boek gebonden?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Row {
                    inventoryFieldState.radioOptions.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                selected = (it == inventoryFieldState.selectedRadioOption),
                                onClick = { inventoryFieldViewModel.setRadioInput(it) },
                            )
                            Text(
                                text = it,
                                fontSize = 18.sp,
                            )
                        }
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                }
            }
        }
    }
}
