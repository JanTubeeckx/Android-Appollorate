package com.example.appollorate.api.login

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApiService {
    @POST("/api/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    @GET("/api/users/{userId}")
    suspend fun getUserDetails()
}
