package com.example.appollorate.compose.inventories

import com.example.appollorate.data.model.InventorySummary

class InventoryOverviewViewModel() {

    data class InventoryScreenState(
        val inventoryList: List<InventorySummary> = mutableListOf(),
    )
}
