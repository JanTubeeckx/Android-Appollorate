package com.example.appollorate.api.inventory

import com.example.appollorate.compose.inventoryfield.InventoryObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InventoryApiService {
    @POST("/api/inventory")
    suspend fun saveInventory(@Body inventoryData: List<InventoryObject>): String

    @GET("/api/inventory/summary")
    suspend fun getInventorySummaries(): Result<List<List<String>>>
}

fun InventoryApiService.getInventorySummariesAsFlow(): Flow<List<List<String>>> = flow {
    getInventorySummaries().onSuccess { emit(it) }.onFailure { println("Error getting inventorysummaries") }
}
