package com.example.appollorate.compose.inventoryfield

data class InventoryFieldState(
    val showDropDown: Map<String, Boolean> = mutableMapOf(),
    var dropDownValue: Map<String, String> = mutableMapOf(),
    val input: Map<String, String> = mutableMapOf(),
    val radioOptions: List<String> = listOf("Ja", "Neen"),
    val selectedRadioOption: String = radioOptions[0],
    val inventoryData: MutableMap<String, String> = mutableMapOf(),
)
