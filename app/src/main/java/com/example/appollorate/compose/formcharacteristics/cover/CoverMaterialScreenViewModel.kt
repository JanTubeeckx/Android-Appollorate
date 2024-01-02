package com.example.appollorate.compose.formcharacteristics.cover

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
import com.example.appollorate.data.model.InventoryField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class CoverMaterialScreenViewModel(
    private val inventoryFieldRepository: InventoryFieldRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    data class CoverMaterialScreenState(
        val inventoryFieldList: List<InventoryField> = mutableListOf(),
    )

    private val _uiState = MutableStateFlow(CoverMaterialScreenState())
    var uiState: StateFlow<CoverMaterialScreenState> = _uiState.asStateFlow()

    private val stepId = savedStateHandle.get<String>("stepId")!!

    init {
        getRepoInventoryFields(stepId)
    }

    fun getRepoInventoryFields(stepId: String) {
        try {
            viewModelScope.launch { inventoryFieldRepository.refresh(stepId.lowercase()) }

            uiState = inventoryFieldRepository.getInventoryFieldsByInventoryStepId(
                stepId.lowercase(),
            ).map { CoverMaterialScreenState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = CoverMaterialScreenState(),
                )
        } catch (e: IOException) {
            Log.e("Error", "$e")
        }
    }

    companion object {
        private var Instance: CoverMaterialScreenViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryFieldRepository = application.container.inventoryFieldRepository
                    val savedStateHandle = createSavedStateHandle()
                    Instance = CoverMaterialScreenViewModel(inventoryFieldRepository = inventoryFieldRepository, savedStateHandle = savedStateHandle)
                }
                Instance!!
            }
        }
    }
}
