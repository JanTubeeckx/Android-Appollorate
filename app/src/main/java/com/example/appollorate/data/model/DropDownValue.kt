package com.example.appollorate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DropDownValue(
    var id: String = "",
    var description: String = "",
    var dropDownField_id: String = "",
)
