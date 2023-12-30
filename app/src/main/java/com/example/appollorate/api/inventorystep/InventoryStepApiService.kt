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
}

fun InventoryStepApiService.getIdentificationInventoryStepsAsFlow(): Flow<List<ApiInventoryStep>> = flow {
    emit(getIdentificationInventorySteps())
}
