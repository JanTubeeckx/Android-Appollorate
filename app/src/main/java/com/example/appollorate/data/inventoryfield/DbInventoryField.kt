package com.example.appollorate.data.inventoryfield

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appollorate.data.model.InventoryField

@Entity(tableName = "inventory_fields")
data class DbInventoryField(
    @PrimaryKey
    val id: String = "",
    val description: String = "",
    val type: String = "",
    val inventoryStep_id: String? = "",
    val dropdownField_id: String? = "",
    val hasDamage: Boolean? = false,
)

fun DbInventoryField.asDomainInventoryField(): InventoryField {
    return InventoryField(
        this.id,
        this.description,
        this.type,
        this.inventoryStep_id,
        this.dropdownField_id,
        this.hasDamage,
    )
}

fun InventoryField.asDbInventoryField(): DbInventoryField {
    return DbInventoryField(
        id = this.id,
        description = this.description,
        type = this.type,
        inventoryStep_id = this.inventoryStep_id,
        dropdownField_id = this.dropdownField_id,
        hasDamage = this.hasDamage,
    )
}

fun List<DbInventoryField>.asDomainInventoryFields(): List<InventoryField> {
    var inventoryFieldList = this.map {
        InventoryField(
            it.id,
            it.description,
            it.type,
            it.inventoryStep_id,
            it.dropdownField_id,
            it.hasDamage,
        )
    }
    return inventoryFieldList
}
