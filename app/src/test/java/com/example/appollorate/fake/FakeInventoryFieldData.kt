package com.example.appollorate.fake

import com.example.appollorate.api.inventoryfield.ApiInventoryField
import com.example.appollorate.data.model.DropDownValue

object FakeInventoryFieldData {
    const val id = "1"
    const val description = "testinventoryfield"
    const val type = "text"
    const val inventoryStepId = "2"
    const val dropDownFieldId = "3"
    const val hasDamage = false
    val dropDownValues = emptyList<DropDownValue>()

    val inventoryFields = listOf(
        ApiInventoryField(
            id,
            description,
            type,
            inventoryStepId,
            dropDownFieldId,
            hasDamage,
            dropDownValues,
        ),
        ApiInventoryField(
            id,
            description,
            type,
            inventoryStepId,
            dropDownFieldId,
            hasDamage,
            dropDownValues,
        ),
        ApiInventoryField(
            id,
            description,
            type,
            inventoryStepId,
            dropDownFieldId,
            hasDamage,
            dropDownValues,
        ),
    )
}
