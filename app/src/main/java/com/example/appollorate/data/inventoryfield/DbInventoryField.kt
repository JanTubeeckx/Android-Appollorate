package com.example.appollorate.data.inventoryfield

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.appollorate.data.database.Converters
import com.example.appollorate.data.model.DropDownValue
import com.example.appollorate.data.model.InventoryField

@Entity(tableName = "inventory_fields")
@TypeConverters(
    Converters.ListTypeConverter::class,
    Converters.DropDownValueTypeConverter::class,
)
data class DbInventoryField(
    @PrimaryKey
    val id: String = "",
    val description: String = "",
    val type: String = "",
    val inventoryStep_id: String? = "",
    val dropDownField_id: String? = "",
    val hasDamage: Boolean? = false,
    val dropDownValues: List<DropDownValue>? = emptyList(),
)

fun DbInventoryField.asDomainInventoryField(): InventoryField {
    return InventoryField(
        this.id,
        this.description,
        this.type,
        this.inventoryStep_id,
        this.dropDownField_id,
        this.hasDamage,
        this.dropDownValues,
    )
}

fun InventoryField.asDbInventoryField(): DbInventoryField {
    return DbInventoryField(
        id = this.id,
        description = this.description,
        type = this.type,
        inventoryStep_id = this.inventoryStep_id,
        dropDownField_id = this.dropDownField_id,
        hasDamage = this.hasDamage,
        dropDownValues = this.dropDownValues,
    )
}

fun List<DbInventoryField>.asDomainInventoryFields(): List<InventoryField> {
    var inventoryFieldList = this.map {
        InventoryField(
            it.id,
            it.description,
            it.type,
            it.inventoryStep_id,
            it.dropDownField_id,
            it.hasDamage,
            it.dropDownValues,
        )
    }
    return inventoryFieldList
}
