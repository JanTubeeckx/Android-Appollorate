package com.example.appollorate.compose.inventoryfield

import com.example.appollorate.data.model.InventoryField

data class InventoryFieldState(
    val showDropDown: Boolean = false,
    val input: Map<String, String> = mutableMapOf(),
    val radioOptions: List<String> = listOf("Ja", "Neen"),
    val selectedRadioOption: String = radioOptions[0],
)

data class InventoryFieldListState(
    val inventoryFieldList: List<InventoryField> = mutableListOf(),
)
