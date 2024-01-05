package com.example.appollorate

import com.example.appollorate.compose.inventoryfield.InventoryFieldViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class InventoryFieldTests {

    @Test
    fun hideDropDownReturnsBoolean() {
        val id = "someId"
        val input = "someInput"
        val viewModel = InventoryFieldViewModel()
        val expectedResult = false
        val actualResult = viewModel.hideDropDown(id, input)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun setInputAddsDataToInventoryData() {
        val id = "someId"
        val input = "someInput"
        val addedData = mutableMapOf(id to input)
        val viewModel = InventoryFieldViewModel()
        val inventoryData = viewModel.uiState.value.inventoryData
        viewModel.setInput(id, input)
        val expectedResult = inventoryData
        val actualResult = addedData
        assertEquals(expectedResult, actualResult)
    }
}
