package com.example.appollorate.viewmodels

import android.text.Spannable.Factory
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
import com.example.appollorate.compose.inventory.InventoryFieldListState
import com.example.appollorate.compose.inventory.InventoryFieldState
import com.example.appollorate.data.inventoryfield.InventoryFieldRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class InventoryFieldViewModel(
    private val inventoryFieldRepository: InventoryFieldRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryFieldState())
    val uiState: StateFlow<InventoryFieldState> = _uiState.asStateFlow()

    lateinit var uiListState: StateFlow<InventoryFieldListState>

    fun showDropDown() {
        _uiState.update { it.copy(showDropDown = true) }
    }

    init {
        val result = getRepoInventoryFields()
        println(result)
    }

    fun getRepoInventoryFields() {
        try {
            viewModelScope.launch { inventoryFieldRepository.refresh() }

            uiListState = inventoryFieldRepository.getInventoryFieldsByInventoryStepId("7f28c5f9-d711-4cd6-ac15-d13d71abaa01").map { InventoryFieldListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = InventoryFieldListState(),
                )
        } catch (e: IOException) {
            Log.e("Error", "$e")
        }
    }

    companion object {
        private var Instance: InventoryFieldViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryFieldRepository = application.container.inventoryFieldRepository
                    Instance = InventoryFieldViewModel(inventoryFieldRepository = inventoryFieldRepository)
                }
                Instance!!
            }
        }
    }
}
