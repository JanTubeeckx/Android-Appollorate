package com.example.appollorate.fake

import com.example.appollorate.api.inventoryfield.ApiInventoryField
import com.example.appollorate.api.inventoryfield.InventoryFieldApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class FakeInventoryFieldApiService : InventoryFieldApiService {
    override suspend fun getInventoryFieldsByInventoryStepId(stepId: String): List<ApiInventoryField> {
        return FakeInventoryFieldData.inventoryFields
    }

    override suspend fun sendImageToBlobStorage(image: MultipartBody.Part?): String {
        TODO("Not yet implemented")
    }

    fun FakeInventoryFieldApiService.getInventoryFieldsAsFlow(stepId: String): Flow<List<ApiInventoryField>> = flow {
        emit(
            getInventoryFieldsByInventoryStepId(stepId = stepId),
        )
    }
}
