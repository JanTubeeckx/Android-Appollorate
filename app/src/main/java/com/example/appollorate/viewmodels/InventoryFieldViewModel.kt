package com.example.appollorate.viewmodels

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
import com.example.appollorate.compose.inventory.InventoryFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InventoryFieldViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryFieldState())
    val uiState: StateFlow<InventoryFieldState> = _uiState.asStateFlow()

    fun showDropDown() {
        _uiState.update { it.copy(showDropDown = true) }
    }

    companion object {
        private var Instance: InventoryFieldViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[APPLICATION_KEY] as AppolloRateApplication)
                    Instance = InventoryFieldViewModel()
                }
                Instance!!
            }
        }
    }
}
