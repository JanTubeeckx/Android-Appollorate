package com.example.appollorate.data.inventoryfield

data class InventoryField(
    var id: String,
    var description: String,
    var type: String,
    var inventoryStepId: String,
    var dropdownFieldId: String,
    var hasDamage: Boolean,
)
