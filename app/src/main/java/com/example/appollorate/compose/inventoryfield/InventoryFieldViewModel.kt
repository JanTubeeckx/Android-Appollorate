package com.example.appollorate.compose.inventoryfield

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
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

    fun setRadioInput(input: String) {
        _uiState.update { it.copy(selectedRadioOption = input) }
    }

    fun setInput(id: String, input: String) {
        val inputValues = uiState.value.input.toMutableMap()
        inputValues[id] = input
        _uiState.update { it.copy(input = inputValues) }
    }

    companion object {
        private var Instance: InventoryFieldViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryFieldRepository = application.container.inventoryFieldRepository
                    Instance = InventoryFieldViewModel()
                }
                Instance!!
            }
        }
    }
}
