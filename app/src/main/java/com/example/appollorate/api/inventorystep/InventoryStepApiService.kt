package com.example.appollorate.api.inventorystep

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface InventoryStepApiService {
    @GET("/api/inventorysteps/identification")
    suspend fun getIdentificationInventorySteps(): List<ApiInventoryStep>

    @GET("/api/inventorysteps/bound")
    suspend fun getDamageInventoryStepsBound(): List<ApiInventoryStep>

    @GET("/api/inventorysteps/loose")
    suspend fun getDamageInventoryStepsLoose(): List<ApiInventoryStep>

    @GET("/api/inventorysteps/formchars")
    suspend fun getFormCharInventorySteps(): List<ApiInventoryStep>
}

fun InventoryStepApiService.getIdentificationInventoryStepsAsFlow(): Flow<List<ApiInventoryStep>> = flow {
    emit(getIdentificationInventorySteps())
}

fun InventoryStepApiService.getDamageInventoryStepsBoundAsFlow(): Flow<List<ApiInventoryStep>> = flow {
    emit(getDamageInventoryStepsBound())
}

fun InventoryStepApiService.getDamageInventoryStepsLooseAsFlow(): Flow<List<ApiInventoryStep>> = flow {
    emit(getDamageInventoryStepsLoose())
}

fun InventoryStepApiService.getFormCharInventoryStepsAsFlow(): Flow<List<ApiInventoryStep>> = flow {
    emit(getFormCharInventorySteps())
}
