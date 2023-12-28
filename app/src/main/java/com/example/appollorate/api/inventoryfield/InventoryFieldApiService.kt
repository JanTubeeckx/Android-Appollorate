package com.example.appollorate.api.inventoryfield

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path

interface InventoryFieldApiService {
    @GET("/api/inventoryfields/{stepId}")
    suspend fun getInventoryFieldsByInventoryStepId(@Path("stepId") stepId: String): List<ApiInventoryField>
}

fun InventoryFieldApiService.getInventoryFieldsAsFlow(): Flow<List<ApiInventoryField>> = flow {
    emit(getInventoryFieldsByInventoryStepId(stepId = "7f28c5f9-d711-4cd6-ac15-d13d71abaa01"))
}
