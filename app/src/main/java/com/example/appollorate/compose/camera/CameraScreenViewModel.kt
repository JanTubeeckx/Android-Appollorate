package com.example.appollorate.compose.camera

import android.Manifest
import android.os.Build
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CameraScreenViewModel : ViewModel() {

    var currentPermissionState = mutableStateOf(false)

    var imageCapture: MutableState<ImageCapture>

    init {
        imageCapture = mutableStateOf(ImageCapture.Builder().build())
    }

    fun setPermissionState(granted: Boolean) {
        currentPermissionState.value = granted
    }

    override fun onCleared() {
        super.onCleared()
    }

    val REQUIRED_PERMISSIONS = mutableListOf(
        Manifest.permission.CAMERA,
    ).apply {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }.toTypedArray()
}
