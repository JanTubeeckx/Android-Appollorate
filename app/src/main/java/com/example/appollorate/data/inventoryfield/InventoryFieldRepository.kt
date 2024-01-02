package com.example.appollorate.data.inventoryfield

import android.util.Log
import com.example.appollorate.api.inventoryfield.InventoryFieldApiService
import com.example.appollorate.api.inventoryfield.asDomainObjects
import com.example.appollorate.api.inventoryfield.getInventoryFieldsAsFlow
import com.example.appollorate.data.model.InventoryField
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface InventoryFieldRepository {
    fun getInventoryFieldsByInventoryStepId(stepId: String): Flow<List<InventoryField>>

    suspend fun insertInventoryField(inventoryField: InventoryField)
    suspend fun refresh(stepId: String)
}

class CachingInventoryFieldRepository(
    private val inventoryFieldDao: InventoryFieldDao,
    private val inventoryFieldApiService: InventoryFieldApiService,
) : InventoryFieldRepository {

    override fun getInventoryFieldsByInventoryStepId(stepId: String): Flow<List<InventoryField>> {
        return inventoryFieldDao.getInventoryFieldsByInventoryStepId(stepId).map {
            it.asDomainInventoryFields()
        }.onEach {
          /*  if (it.isEmpty()) {
                refresh()
            }*/
        }
    }

    override suspend fun insertInventoryField(inventoryField: InventoryField) {
        inventoryFieldDao.insert(inventoryField.asDbInventoryField())
    }

    override suspend fun refresh(stepId: String) {
        try {
            inventoryFieldApiService.getInventoryFieldsAsFlow(stepId).asDomainObjects().collect() {
                    value ->
                Log.i("TestRefresh", "$value")
                for (inventoryField in value) {
                    insertInventoryField(inventoryField)
                }
                getInventoryFieldsByInventoryStepId(stepId)
            }
        } catch (e: SocketTimeoutException) {
            Log.e("Error", "$e")
        }
    }
}
