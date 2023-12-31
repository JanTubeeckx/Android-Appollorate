package com.example.appollorate.compose.inventory

import com.example.appollorate.data.model.InventoryField

data class InventoryScreenState(
    val inventoryFieldList: List<InventoryField> = mutableListOf(),
)
