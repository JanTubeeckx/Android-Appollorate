package com.example.appollorate.data.inventory

import android.util.Log
import com.example.appollorate.api.inventory.InventoryApiService
import com.example.appollorate.api.inventory.getInventorySummariesAsFlow
import com.example.appollorate.data.model.InventorySummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface InventoryRepository {
    fun getInventorySummaries(): Flow<List<InventorySummary>>

    suspend fun insertInventorySummary(inventorySummary: InventorySummary)

    suspend fun refresh()
}

class CachingInventoryRepository(
    private val inventoryDao: InventoryDao,
    private val inventoryApiService: InventoryApiService,
) : InventoryRepository {
    override fun getInventorySummaries(): Flow<List<InventorySummary>> {
        return inventoryDao.getInventorySummaries().map {
            it.asDomainInventorySummaries()
        }.onEach { }
    }

    override suspend fun insertInventorySummary(inventorySummary: InventorySummary) {
        inventoryDao.insert(inventorySummary.asDbInventorySummary())
    }

    override suspend fun refresh() {
        try {
            inventoryApiService.getInventorySummariesAsFlow().collect() {
                    value ->
                for (inventorySummary in value) {
                    val newInventorySummary = InventorySummary(
                        id = inventorySummary[5],
                        title = inventorySummary[1],
                        imagePath = inventorySummary[2],
                        dateTime = inventorySummary[0],
                        rating = inventorySummary[4].toInt(),
                    )
                    insertInventorySummary(newInventorySummary)
                }
                getInventorySummaries()
            }
        } catch (e: SocketTimeoutException) {
            Log.e("Error", "$e")
        }
    }
}
