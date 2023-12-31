package com.example.appollorate.data.model

data class InventoryField(
    var id: String,
    var description: String,
    var type: String,
    var inventoryStep_id: String?,
    var dropDownField_id: String?,
    var hasDamage: Boolean?,
    var dropDownValues: List<DropDownValue>? = emptyList(),
)
