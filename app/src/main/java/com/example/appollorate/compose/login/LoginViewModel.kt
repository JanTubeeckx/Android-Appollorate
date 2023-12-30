package com.example.appollorate.compose.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
import com.example.appollorate.api.login.LoginRequest
import com.example.appollorate.data.login.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    var loginApiState: LoginApiState by mutableStateOf(LoginApiState.Loading)
        private set

    fun setEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun setPassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            val loginRequest = LoginRequest(
                email = _uiState.value.email,
                password = _uiState.value.password,
            )
            val result = loginRepository.login(loginRequest)
            if (result != null) {
                loginApiState = LoginApiState.Success
            }
            Log.i("Test", "$result")
        }
    }

    fun cancel() {
        _uiState.update { currentState ->
            currentState.copy(
                email = "",
                password = "",
            )
        }
    }

    companion object {
        private var Instance: LoginViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val loginRepository = application.container.loginRepository
                    Instance = LoginViewModel(loginRepository = loginRepository)
                }
                Instance!!
            }
        }
    }
}
