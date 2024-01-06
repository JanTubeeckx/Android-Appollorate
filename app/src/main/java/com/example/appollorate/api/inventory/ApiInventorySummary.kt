package com.example.appollorate.api.inventory

import com.example.appollorate.data.model.InventorySummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiInventorySummary(
    val id: String,
    val title: String,
    val imagePath: String,
    val dateTime: String,
    val rating: Int,
)

fun Flow<List<ApiInventorySummary>>.asDomainObjects(): Flow<List<InventorySummary>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ApiInventorySummary>.asDomainObjects(): List<InventorySummary> {
    var domainList = this.map {
        InventorySummary(
            it.id,
            it.title,
            it.imagePath,
            it.dateTime,
            it.rating,
        )
    }
    return domainList
}
