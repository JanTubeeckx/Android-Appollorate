package com.example.appollorate.compose.identification

import android.net.Uri
import com.example.appollorate.data.model.InventoryField

data class IdentificationScreenState(
    val inventoryFieldList: List<InventoryField> = mutableListOf(),
    val openCamera: Boolean = false,
    val showImage: Boolean = false,
    val photoUri: Uri = Uri.EMPTY,
    val imageName: String = "",
)
