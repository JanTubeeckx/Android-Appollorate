package com.example.appollorate.fake

import com.example.appollorate.api.login.LoginRequest

object FakeLoginData {
    const val email = "test@test.com"
    const val password = "testpassword"

    val loginRequest = LoginRequest(email, password)
}
