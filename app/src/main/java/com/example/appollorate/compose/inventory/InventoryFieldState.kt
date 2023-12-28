package com.example.appollorate.compose.inventory

import com.example.appollorate.data.inventoryfield.InventoryField

data class InventoryFieldState(
    val showDropDown: Boolean = false,
)

data class InventoryFieldListState(
    val inventoryFieldList: List<InventoryField> = listOf(),
)
