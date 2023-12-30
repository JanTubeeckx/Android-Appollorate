package com.example.appollorate.api.inventorystep

import com.example.appollorate.data.model.InventoryStep
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiInventoryStep(
    val id: String,
    val description: String,
    val icon: String,
    val order: Int,
    val isDamage: Boolean,
    val isBound: Boolean,
    val inventoryStepId: String?,
)

fun Flow<List<ApiInventoryStep>>.asDomainObjects(): Flow<List<InventoryStep>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ApiInventoryStep>.asDomainObjects(): List<InventoryStep> {
    var domainList = this.map {
        InventoryStep(
            it.id,
            it.description,
            it.icon,
            it.order,
            it.isDamage,
            it.isBound,
            it.inventoryStepId,
        )
    }
    return domainList
}
