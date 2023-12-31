package com.example.appollorate.compose.protection

import com.example.appollorate.data.model.InventoryField

data class ProtectionScreenState(
    val inventoryFieldList: List<InventoryField> = mutableListOf(),
)
