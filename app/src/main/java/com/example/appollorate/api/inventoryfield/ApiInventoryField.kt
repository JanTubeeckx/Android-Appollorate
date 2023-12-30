package com.example.appollorate.api.inventoryfield

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
    val dropdownField_id: String?,
    val hasDamage: Boolean?,
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
            it.dropdownField_id,
            it.hasDamage,
        )
    }
    return domainList
}
