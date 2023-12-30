package com.example.appollorate.data.inventorystep

import android.util.Log
import com.example.appollorate.api.inventorystep.InventoryStepApiService
import com.example.appollorate.api.inventorystep.asDomainObjects
import com.example.appollorate.api.inventorystep.getIdentificationInventoryStepsAsFlow
import com.example.appollorate.data.model.InventoryStep
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface InventoryStepRepository {
    fun getIdentificationInventorySteps(): Flow<List<InventoryStep>>

    fun getDamageInventoryStepsBound(): Flow<List<InventoryStep>>

    fun getDamageInventoryStepsLoose(): Flow<List<InventoryStep>>

    suspend fun insertInventoryStep(inventoryStep: InventoryStep)

    suspend fun refresh()
}

class CachingInventoryStepRepository(
    private val inventoryStepDao: InventoryStepDao,
    private val inventoryStepApiService: InventoryStepApiService,
) : InventoryStepRepository {
    override fun getIdentificationInventorySteps(): Flow<List<InventoryStep>> {
        return inventoryStepDao.getIdentificationInventorySteps().map {
            it.asDomainInventorySteps()
        }.onEach {
            /*  if (it.isEmpty()) {
                  refresh()
              }*/
        }
    }

    override fun getDamageInventoryStepsBound(): Flow<List<InventoryStep>> {
        TODO("Not yet implemented")
    }

    override fun getDamageInventoryStepsLoose(): Flow<List<InventoryStep>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertInventoryStep(inventoryStep: InventoryStep) {
        inventoryStepDao.insert(inventoryStep.asDbInventoryStep())
    }

    override suspend fun refresh() {
        try {
            inventoryStepApiService.getIdentificationInventoryStepsAsFlow().asDomainObjects().collect() {
                    value ->
                for (inventoryStep in value) {
                    insertInventoryStep(inventoryStep)
                }
                getIdentificationInventorySteps()
            }
        } catch (e: SocketTimeoutException) {
            Log.e("Error", "$e")
        }
    }
}
