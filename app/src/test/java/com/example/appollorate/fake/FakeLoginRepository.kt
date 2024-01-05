package com.example.appollorate.fake

import com.example.appollorate.api.login.LoginRequest
import com.example.appollorate.data.login.LoginRepository

class FakeLoginRepository : LoginRepository {

    val loginRequest = FakeLoginData.loginRequest
    val token = "sometoken"
    override suspend fun login(loginRequest: LoginRequest): String {
        return token
    }
}
