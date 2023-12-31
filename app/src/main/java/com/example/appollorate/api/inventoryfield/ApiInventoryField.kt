package com.example.appollorate.api.inventoryfield

import com.example.appollorate.data.model.DropDownValue
import com.example.appollorate.data.model.InventoryField
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiInventoryField(
    val id: String,
    val description: String,
    val type: String,
    val inventoryStep_id: String?,
    val dropDownField_id: String?,
    val hasDamage: Boolean?,
    val dropdownvalues: List<DropDownValue>?,
)

fun Flow<List<ApiInventoryField>>.asDomainObjects(): Flow<List<InventoryField>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ApiInventoryField>.asDomainObjects(): List<InventoryField> {
    var domainList = this.map {
        InventoryField(
            it.id,
            it.description,
            it.type,
            it.inventoryStep_id,
            it.dropDownField_id,
            it.hasDamage,
            it.dropdownvalues,
        )
    }
    return domainList
}
