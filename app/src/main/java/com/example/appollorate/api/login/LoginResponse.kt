package com.example.appollorate.api.login

import com.example.appollorate.data.model.User

data class LoginResponse(
    val user: User,
    val token: String,
)
