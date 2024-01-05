package com.example.appollorate.compose.identification

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appollorate.AppolloRateApplication
import com.example.appollorate.api.inventoryfield.InventoryFieldApiService
import com.example.appollorate.data.inventoryfield.InventoryFieldRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

@SuppressLint("StaticFieldLeak")
class IdentificationScreenViewModel(
    private val inventoryFieldRepository: InventoryFieldRepository,
    private val inventoryFieldApiService: InventoryFieldApiService,
    private val savedStateHandle: SavedStateHandle,
    private val context: Context,
) : ViewModel() {

    private val _uiState = MutableStateFlow(IdentificationScreenState())
    var uiState: StateFlow<IdentificationScreenState> = _uiState.asStateFlow()

    private val stepId = savedStateHandle.get<String>("stepId")!!

    init {
        getRepoInventoryFields(stepId)
    }

    fun getRepoInventoryFields(stepId: String) {
        try {
            viewModelScope.launch { inventoryFieldRepository.refresh(stepId.lowercase()) }

            uiState = inventoryFieldRepository.getInventoryFieldsByInventoryStepId(
                stepId.lowercase(),
            ).map { IdentificationScreenState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = IdentificationScreenState(),
                )
        } catch (e: IOException) {
            Log.e("Error", "$e")
        }
    }

    fun getRealPathFromImageURI(context: Context, contentUri: Uri?): String? {
        var cursor: Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri!!, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } finally {
            cursor?.close()
        }
    }

    fun sendImage(photoUri: Uri): String {
        val mediaType = "image/png"
        val fileName: String = "AppolloRateAndroid." + System.currentTimeMillis() + ".png"
        _uiState.update { it.copy(imageName = fileName) }
        val imagePath = getRealPathFromImageURI(context, photoUri)
        val file = File(imagePath!!)

        val reqFile = file.asRequestBody(mediaType.toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("data", fileName, reqFile)
        viewModelScope.launch {
            inventoryFieldApiService.sendImageToBlobStorage(image)
        }
        return _uiState.value.imageName
    }

    companion object {
        private var Instance: IdentificationScreenViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppolloRateApplication)
                    val inventoryFieldRepository = application.container.inventoryFieldRepository
                    val inventoryFieldApiService = application.container.inventoryFieldApiService
                    val savedStateHandle = createSavedStateHandle()
                    val context = application.applicationContext
                    Instance = IdentificationScreenViewModel(
                        inventoryFieldRepository = inventoryFieldRepository,
                        inventoryFieldApiService = inventoryFieldApiService,
                        savedStateHandle = savedStateHandle,
                        context = context,
                    )
                }
                Instance!!
            }
        }
    }
}
