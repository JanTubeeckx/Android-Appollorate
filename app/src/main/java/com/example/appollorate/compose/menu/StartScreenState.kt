package com.example.appollorate.compose.menu

import com.example.appollorate.data.model.InventoryStep

data class StartScreenState(
    val identificationInventorySteps: List<InventoryStep> = mutableListOf(),
)
