package com.example.appollorate.compose.inventoryfield

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
import com.example.appollorate.api.inventory.InventoryApiService
import com.example.appollorate.data.login.LoginPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@SuppressLint("StaticFieldLeak")
class InventoryFieldViewModel(
    private val inventoryApiService: InventoryApiService,
    private val context: Context,
) : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryFieldState())
    val uiState: StateFlow<InventoryFieldState> = _uiState.asStateFlow()
    var completedInventory = _uiState.value.completedInventoryData
    private val loginPreferences = LoginPreferences(context)

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
        _uiState.value.inventoryData[id] = value
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

    fun sendInput() {
        val userName = runBlocking { loginPreferences.getUserName() }!!
        completedInventory = _uiState.value.inventoryData.map { InventoryObject(it.key, it.value) }.toMutableList()
        completedInventory.add(InventoryObject("user", userName))
        viewModelScope.launch {
            inventoryApiService.saveInventory(completedInventory)
        }
    }

    companion object {
        private var Instance: InventoryFieldViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryApiService = application.container.inventoryApiService
                    val context = application.applicationContext
                    Instance = InventoryFieldViewModel(inventoryApiService = inventoryApiService, context = context)
                }
                Instance!!
            }
        }
    }
}
