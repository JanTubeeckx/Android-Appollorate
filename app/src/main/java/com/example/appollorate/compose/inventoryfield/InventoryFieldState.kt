package com.example.appollorate.compose.inventoryfield

import com.example.appollorate.data.model.InventoryField

data class InventoryFieldState(
    val showDropDown: Boolean = false,
    val input: Map<String, String> = mutableMapOf(),
)

data class InventoryFieldListState(
    val inventoryFieldList: List<InventoryField> = mutableListOf(),
)
