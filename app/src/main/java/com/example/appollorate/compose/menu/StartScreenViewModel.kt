package com.example.appollorate.compose.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
import com.example.appollorate.data.inventorystep.InventoryStepRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class StartScreenViewModel(
    private val inventoryStepRepository: InventoryStepRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StartScreenState())
    var uiState: StateFlow<StartScreenState> = _uiState.asStateFlow()

    init {
        getRepoInventorySteps()
    }

    fun getRepoInventorySteps() {
        try {
            viewModelScope.launch { inventoryStepRepository.refresh() }

            uiState = inventoryStepRepository.getIdentificationInventorySteps().map { StartScreenState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = StartScreenState(),
                )
        } catch (e: IOException) {
            Log.e("Error", "$e")
        }
    }

    companion object {
        private var Instance: StartScreenViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryStepRepository = application.container.inventoryStepRepository
                    Instance = StartScreenViewModel(inventoryStepRepository = inventoryStepRepository)
                }
                Instance!!
            }
        }
    }
}
