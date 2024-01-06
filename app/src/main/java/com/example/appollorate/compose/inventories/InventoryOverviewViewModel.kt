package com.example.appollorate.compose.inventories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
import com.example.appollorate.data.inventory.InventoryRepository
import com.example.appollorate.data.model.InventorySummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class InventoryOverviewViewModel(
    private val inventoryRepository: InventoryRepository,
) : ViewModel() {

    data class InventoryScreenState(
        val inventoryList: List<InventorySummary> = mutableListOf(),
    )

    private val _uiState = MutableStateFlow(InventoryScreenState())
    var uiState: StateFlow<InventoryScreenState> = _uiState.asStateFlow()

    init {
        val result = getRepoInventorySummaries()
        println(result)
    }

    fun getRepoInventorySummaries() {
        try {
            viewModelScope.launch { inventoryRepository.refresh() }

            uiState = inventoryRepository.getInventorySummaries().map { InventoryScreenState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = InventoryScreenState(),
                )
        } catch (e: IOException) {
            Log.e("Error", "$e")
        }
    }

    companion object {
        private var Instance: InventoryOverviewViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryRepository = application.container.inventoryRepository
                    Instance = InventoryOverviewViewModel(inventoryRepository = inventoryRepository)
                }
                Instance!!
            }
        }
    }
}
