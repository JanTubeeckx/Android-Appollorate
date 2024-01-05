package com.example.appollorate.fake

import com.example.appollorate.api.inventoryfield.asDomainObjects
import com.example.appollorate.data.inventoryfield.InventoryFieldRepository
import com.example.appollorate.data.model.InventoryField
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeInventoryFieldRepository(private val fakeInventoryFieldApiService: FakeInventoryFieldApiService) : InventoryFieldRepository {
    override fun getInventoryFieldsByInventoryStepId(stepId: String): Flow<List<InventoryField>> {
        return flow { fakeInventoryFieldApiService.getInventoryFieldsByInventoryStepId("1").asDomainObjects() }
    }

    override suspend fun insertInventoryField(inventoryField: InventoryField) {
        TODO("Not yet implemented")
    }

    override suspend fun refresh(stepId: String) {
        TODO("Not yet implemented")
    }
}
