package com.example.appollorate.data.inventorystep

import android.util.Log
import com.example.appollorate.api.inventorystep.InventoryStepApiService
import com.example.appollorate.api.inventorystep.asDomainObjects
import com.example.appollorate.api.inventorystep.getDamageInventoryStepsBoundAsFlow
import com.example.appollorate.api.inventorystep.getDamageInventoryStepsLooseAsFlow
import com.example.appollorate.api.inventorystep.getFormCharInventoryStepsAsFlow
import com.example.appollorate.api.inventorystep.getIdentificationInventoryStepsAsFlow
import com.example.appollorate.data.model.InventoryStep
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface InventoryStepRepository {
    fun getIdentificationInventorySteps(): Flow<List<InventoryStep>>

    fun getFormCharInventorySteps(): Flow<List<InventoryStep>>

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

    override fun getFormCharInventorySteps(): Flow<List<InventoryStep>> {
        return inventoryStepDao.getFormCharInventorySteps().map {
            it.asDomainInventorySteps()
        }
    }

    override fun getDamageInventoryStepsBound(): Flow<List<InventoryStep>> {
        return inventoryStepDao.getDamageInventoryStepsBound().map {
            it.asDomainInventorySteps()
        }
    }

    override fun getDamageInventoryStepsLoose(): Flow<List<InventoryStep>> {
        return inventoryStepDao.getDamageInventoryStepsLoose().map {
            it.asDomainInventorySteps()
        }
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
            inventoryStepApiService.getDamageInventoryStepsBoundAsFlow().asDomainObjects().collect() {
                    value ->
                for (inventoryStep in value) {
                    insertInventoryStep(inventoryStep)
                }
                getDamageInventoryStepsBound()
            }
            inventoryStepApiService.getDamageInventoryStepsLooseAsFlow().asDomainObjects().collect() {
                    value ->
                for (inventoryStep in value) {
                    insertInventoryStep(inventoryStep)
                }
                getDamageInventoryStepsLoose()
            }
            inventoryStepApiService.getFormCharInventoryStepsAsFlow().asDomainObjects().collect() {
                    value ->
                for (inventoryStep in value) {
                    insertInventoryStep(inventoryStep)
                }
                getFormCharInventorySteps()
            }
        } catch (e: SocketTimeoutException) {
            Log.e("Error", "$e")
        }
    }
}
