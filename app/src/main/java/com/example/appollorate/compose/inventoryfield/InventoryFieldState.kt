package com.example.appollorate.compose.inventoryfield

data class InventoryFieldState(
    val showDropDown: Boolean = false,
    val input: Map<String, String> = mutableMapOf(),
    val radioOptions: List<String> = listOf("Ja", "Neen"),
    val selectedRadioOption: String = radioOptions[0],
)
