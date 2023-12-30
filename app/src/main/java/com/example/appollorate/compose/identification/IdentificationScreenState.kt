package com.example.appollorate.compose.identification

import com.example.appollorate.data.model.InventoryField

data class IdentificationScreenState(
    val identificationInventoryFieldList: List<InventoryField> = mutableListOf(),
)
