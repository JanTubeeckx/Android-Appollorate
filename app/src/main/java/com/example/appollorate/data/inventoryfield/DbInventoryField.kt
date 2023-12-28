package com.example.appollorate.data.inventoryfield

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_fields")
data class DbInventoryField(
    @PrimaryKey
    val id: String = "",
    val description: String = "",
    val type: String = "",
    val inventoryStepId: String = "",
    val dropdownFieldId: String = "",
    val hasDamage: Boolean = false,
)

fun DbInventoryField.asDomainInventoryField(): InventoryField {
    return InventoryField(
        this.id,
        this.description,
        this.type,
        this.inventoryStepId,
        this.dropdownFieldId,
        this.hasDamage,
    )
}

fun InventoryField.asDbInventoryField(): DbInventoryField {
    return DbInventoryField(
        id = this.id,
        description = this.description,
        type = this.type,
        inventoryStepId = this.inventoryStepId,
        dropdownFieldId = this.dropdownFieldId,
        hasDamage = this.hasDamage,
    )
}

fun List<DbInventoryField>.asDomainInventoryFields(): List<InventoryField> {
    var inventoryFieldList = this.map {
        InventoryField(
            it.id,
            it.description,
            it.type,
            it.inventoryStepId,
            it.dropdownFieldId,
            it.hasDamage,
        )
    }
    return inventoryFieldList
}
