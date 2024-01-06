package com.example.appollorate.data.login

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.appollorate.api.login.LoginApiService
import com.example.appollorate.api.login.LoginRequest
import com.example.appollorate.api.login.LoginResponse

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>
}

class LoginRepositoryImpl(
    private val loginApiService: LoginApiService,
    private val preferences: LoginPreferences,
) : LoginRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return loginApiService.loginUser(loginRequest)
    }
}
