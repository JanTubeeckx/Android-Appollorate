package com.example.appollorate.data.model

data class InventoryStep(
    var id: String,
    var description: String,
    var icon: String,
    var order: Int,
    var isDamage: Boolean,
    var isBound: Boolean,
    var inventoryStepId: String?,
)
