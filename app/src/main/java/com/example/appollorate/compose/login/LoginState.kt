package com.example.appollorate.compose.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val showLogin: Boolean = true,
)

sealed interface LoginApiState {
    object Success : LoginApiState
    object Error : LoginApiState
    object Loading : LoginApiState
}
