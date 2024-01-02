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
import com.example.appollorate.data.inventorystep.InventoryStepRepository
import com.example.appollorate.data.model.InventoryStep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class CoverFormCharsViewModel(
    private val inventoryStepRepository: InventoryStepRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    data class CoverFormCharsState(
        val coverFormCharInventorySteps: List<InventoryStep> = mutableListOf(),
    )

    private val _uiState = MutableStateFlow(CoverFormCharsState())
    var uiState: StateFlow<CoverFormCharsState> = _uiState.asStateFlow()

    private val stepId = savedStateHandle.get<String>("stepId")!!.lowercase()

    init {
        getRepoInventorySteps()
    }

    fun getRepoInventorySteps() {
        try {
            viewModelScope.launch { inventoryStepRepository.refresh() }

            uiState = inventoryStepRepository.getFormCharInventorySteps().map { CoverFormCharsState(it.filter { i -> i.inventoryStepId == stepId }) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = CoverFormCharsState(),
                )
        } catch (e: IOException) {
            Log.e("Error", "$e")
        }
    }

    companion object {
        private var Instance: CoverFormCharsViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryStepRepository = application.container.inventoryStepRepository
                    val savedStateHandle = createSavedStateHandle()
                    Instance = CoverFormCharsViewModel(inventoryStepRepository = inventoryStepRepository, savedStateHandle = savedStateHandle)
                }
                Instance!!
            }
        }
    }
}
