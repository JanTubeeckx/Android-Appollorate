package com.example.appollorate.compose.identification

import android.util.Log
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class IdentificationScreenViewModel(
    private val inventoryFieldRepository: InventoryFieldRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(IdentificationScreenState())
    var uiState: StateFlow<IdentificationScreenState> = _uiState.asStateFlow()

    private val stepId = savedStateHandle.get<String>("stepId")!!

    init {
        getRepoInventoryFields(stepId)
    }

    fun getRepoInventoryFields(stepId: String) {
        try {
            viewModelScope.launch { inventoryFieldRepository.refresh(stepId.lowercase()) }

            uiState = inventoryFieldRepository.getInventoryFieldsByInventoryStepId(
                stepId.lowercase(),
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

    fun openCamera() {
        println("test")
        _uiState.update {
            println(it.openCamera)
            it.copy(openCamera = !_uiState.value.openCamera)
        }
    }

    companion object {
        private var Instance: IdentificationScreenViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryFieldRepository = application.container.inventoryFieldRepository
                    val savedStateHandle = createSavedStateHandle()
                    Instance = IdentificationScreenViewModel(inventoryFieldRepository = inventoryFieldRepository, savedStateHandle = savedStateHandle)
                }
                Instance!!
            }
        }
    }
}
