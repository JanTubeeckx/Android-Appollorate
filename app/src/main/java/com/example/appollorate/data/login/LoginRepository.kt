package com.example.appollorate.data.login

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.appollorate.api.login.LoginApiService
import com.example.appollorate.api.login.LoginRequest
import java.io.IOException

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): String
}

class LoginRepositoryImpl(
    private val loginApiService: LoginApiService,
    private val preferences: LoginPreferences,
) : LoginRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun login(loginRequest: LoginRequest): String {
        val response = loginApiService.loginUser(loginRequest)
        try {
            preferences.saveLoginToken(response.token)
        } catch (e: IOException) {
            Log.i("Error", "${e.message}")
        } catch (e: HttpException) {
            Log.i("Error", "${e.message}")
        }
        return response.token
    }
}
