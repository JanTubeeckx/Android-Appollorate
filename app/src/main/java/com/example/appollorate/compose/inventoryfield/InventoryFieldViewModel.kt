package com.example.appollorate.compose.inventoryfield

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InventoryFieldViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryFieldState())
    val uiState: StateFlow<InventoryFieldState> = _uiState.asStateFlow()

    fun showDropDown(id: String): Boolean {
        val dropDownState = uiState.value.showDropDown.toMutableMap()
        dropDownState[id] = true
        _uiState.update { it.copy(showDropDown = dropDownState) }
        return dropDownState[id]!!
    }

    fun hideDropDown(id: String, value: String): Boolean {
        val dropDownState = uiState.value.showDropDown.toMutableMap()
        dropDownState[id] = false
        val dropDownValue = uiState.value.dropDownValue.toMutableMap()
        dropDownValue[id] = value
        _uiState.update {
            it.copy(
                showDropDown = dropDownState,
                dropDownValue = dropDownValue,
            )
        }
        return dropDownState[id]!!
    }

    fun setRadioInput(id: String, input: String) {
        _uiState.update { it.copy(selectedRadioOption = input) }
        _uiState.value.inventoryData[id] = input
    }

    fun setInput(id: String, input: String) {
        val inputValues = uiState.value.input.toMutableMap()
        inputValues[id] = input
        _uiState.update { it.copy(input = inputValues) }
        _uiState.value.inventoryData[id] = input
    }

    companion object {
        private var Instance: InventoryFieldViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    Instance = InventoryFieldViewModel()
                }
                Instance!!
            }
        }
    }
}
