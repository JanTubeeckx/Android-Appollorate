package com.example.appollorate.data.inventoryfield

import android.util.Log
import com.example.appollorate.api.inventoryfield.InventoryFieldApiService
import com.example.appollorate.api.inventoryfield.asDomainObjects
import com.example.appollorate.api.inventoryfield.getInventoryFieldsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface InventoryFieldRepository {
    fun getInventoryFieldsByInventoryStepId(inventoryStepId: String): Flow<List<InventoryField>>

    suspend fun insertInventoryField(inventoryField: InventoryField)
    suspend fun refresh()
}

class CachingInventoryFieldRepository(
    private val inventoryFieldDao: InventoryFieldDao,
    private val inventoryFieldApiService: InventoryFieldApiService,
) : InventoryFieldRepository {
    override fun getInventoryFieldsByInventoryStepId(stepId: String): Flow<List<InventoryField>> {
        return inventoryFieldDao.getIdentificationInventoryFieldsByInventoryStepId(stepId).map {
            it.asDomainInventoryFields()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override suspend fun insertInventoryField(inventoryField: InventoryField) {
        inventoryFieldDao.insert(inventoryField.asDbInventoryField())
    }

    override suspend fun refresh() {
        try {
            inventoryFieldApiService.getInventoryFieldsAsFlow().asDomainObjects().collect() {
                    value ->
                for (inventoryField in value) {
                    insertInventoryField(inventoryField)
                }
                getInventoryFieldsByInventoryStepId(stepId = "7f28c5f9-d711-4cd6-ac15-d13d71abaa01")
            }
        } catch (e: SocketTimeoutException) {
            Log.e("Error", "$e")
        }
    }
}
