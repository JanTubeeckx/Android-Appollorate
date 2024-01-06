package com.example.appollorate.api.inventory

import com.example.appollorate.compose.inventoryfield.InventoryObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InventoryApiService {
    @POST("/api/inventory")
    suspend fun saveInventory(@Body inventoryData: List<InventoryObject>): String

    @GET("/api/inventory/summary")
    suspend fun getInventorySummaries(): List<ApiInventorySummary>
}
