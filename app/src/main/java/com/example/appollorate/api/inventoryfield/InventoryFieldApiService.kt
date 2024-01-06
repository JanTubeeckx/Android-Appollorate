package com.example.appollorate.api.inventoryfield

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface InventoryFieldApiService {
    @GET("/api/inventoryfields/{stepId}")
    suspend fun getInventoryFieldsByInventoryStepId(@Path("stepId") stepId: String): List<ApiInventoryField>

    @Multipart
    @POST("/api/inventory/image")
    suspend fun sendImageToBlobStorage(@Part image: MultipartBody.Part?): String
}

fun InventoryFieldApiService.getInventoryFieldsAsFlow(stepId: String): Flow<List<ApiInventoryField>> = flow {
    emit(getInventoryFieldsByInventoryStepId(stepId = stepId))
}
