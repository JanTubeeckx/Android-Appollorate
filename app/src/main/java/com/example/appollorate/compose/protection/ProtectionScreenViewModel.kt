package com.example.appollorate.compose.protection

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
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

class ProtectionScreenViewModel(
    private val inventoryFieldRepository: InventoryFieldRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProtectionScreenState())
    var uiState: StateFlow<ProtectionScreenState> = _uiState.asStateFlow()

    private val stepId = savedStateHandle.get<String>("stepId")!!

    init {
        getRepoInventoryFields()
    }

    fun getRepoInventoryFields() {
        try {
            viewModelScope.launch { inventoryFieldRepository.refresh(stepId.toLowerCase()) }

            uiState = inventoryFieldRepository.getInventoryFieldsByInventoryStepId(
                stepId.toLowerCase(),
            ).map { ProtectionScreenState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = ProtectionScreenState(),
                )
        } catch (e: IOException) {
            Log.e("Error", "$e")
        }
    }

    companion object {
        private var Instance: ProtectionScreenViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application =
                        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryFieldRepository = application.container.inventoryFieldRepository
                    val savedStateHandle = createSavedStateHandle()
                    Instance = ProtectionScreenViewModel(
                        inventoryFieldRepository = inventoryFieldRepository,
                        savedStateHandle = savedStateHandle,
                    )
                }
                Instance!!
            }
        }
    }
}
