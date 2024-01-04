package com.example.appollorate.compose.camera

import android.Manifest
import android.os.Build
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
import com.example.appollorate.api.inventoryfield.InventoryFieldApiService
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class CameraScreenViewModel(
    private val inventoryFieldApiService: InventoryFieldApiService,
) : ViewModel() {

    var currentPermissionState = mutableStateOf(false)

    var imageCapture: MutableState<ImageCapture> = mutableStateOf(ImageCapture.Builder().build())

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

    fun sendImage(output: String?) {
        val mediaType: String = "image/png"
        val fileName: String = "AppolloRateAndroid" + System.currentTimeMillis() + ".png"
        val file: File = File(output)

        val reqFile = file.asRequestBody(mediaType.toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("data", fileName, reqFile)
        viewModelScope.launch {
            inventoryFieldApiService.sendImageToBlobStorage(image)
        }
    }

    companion object {
        private var Instance: CameraScreenViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryFieldApiService = application.container.inventoryFieldApiService
                    Instance = CameraScreenViewModel(inventoryFieldApiService = inventoryFieldApiService)
                }
                Instance!!
            }
        }
    }
}
