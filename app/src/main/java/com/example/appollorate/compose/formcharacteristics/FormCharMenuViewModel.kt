package com.example.appollorate.compose.formcharacteristics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class FormCharMenuViewModel(
    private val inventoryStepRepository: InventoryStepRepository,
) : ViewModel() {
    data class FormCharMenuState(
        val FormAndDamageInventorySteps: List<InventoryStep> = mutableListOf(),
    )

    private val _uiState = MutableStateFlow(FormCharMenuState())
    var uiState: StateFlow<FormCharMenuState> = _uiState.asStateFlow()

    init {
        getRepoInventorySteps()
    }

    fun getRepoInventorySteps() {
        try {
            viewModelScope.launch { inventoryStepRepository.refresh() }

            uiState = inventoryStepRepository.getDamageInventoryStepsBound().map { FormCharMenuState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = FormCharMenuState(),
                )
        } catch (e: IOException) {
            Log.e("Error", "$e")
        }
    }

    companion object {
        private var Instance: FormCharMenuViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryStepRepository = application.container.inventoryStepRepository
                    Instance = FormCharMenuViewModel(inventoryStepRepository = inventoryStepRepository)
                }
                Instance!!
            }
        }
    }
}
