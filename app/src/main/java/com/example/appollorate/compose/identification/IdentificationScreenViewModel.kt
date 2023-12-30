package com.example.appollorate.compose.identification

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
import com.example.appollorate.data.inventoryfield.InventoryFieldRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class IdentificationScreenViewModel(
    private val inventoryFieldRepository: InventoryFieldRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(IdentificationScreenState())
    var uiState: StateFlow<IdentificationScreenState> = _uiState.asStateFlow()

    init {
        getRepoInventoryFields()
    }

    fun getRepoInventoryFields() {
        try {
            viewModelScope.launch { inventoryFieldRepository.refresh() }

            uiState = inventoryFieldRepository.getInventoryFieldsByInventoryStepId(
                "7f28c5f9-d711-4cd6-ac15-d13d71abaa01",
            ).map { IdentificationScreenState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = IdentificationScreenState(),
                )
        } catch (e: IOException) {
            Log.e("Error", "$e")
        }
    }

    companion object {
        private var Instance: IdentificationScreenViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryFieldRepository = application.container.inventoryFieldRepository
                    Instance = IdentificationScreenViewModel(inventoryFieldRepository = inventoryFieldRepository)
                }
                Instance!!
            }
        }
    }
}
