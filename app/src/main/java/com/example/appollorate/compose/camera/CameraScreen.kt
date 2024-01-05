package com.example.appollorate.compose.camera

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.icu.text.DateFormat
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CameraScreen(
    cameraScreenViewModel: CameraScreenViewModel = viewModel(factory = CameraScreenViewModel.Factory),
    onImageCaptured: (Uri) -> Unit,
) {
    val context = LocalContext.current
    val permissionState = remember { cameraScreenViewModel.currentPermissionState }

    cameraScreenViewModel.setPermissionState(
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA,
        ) == PackageManager.PERMISSION_GRANTED,
    )

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            // Handle Permission granted/rejected
            var permissionGranted = true
            permissions.entries.forEach {
                if (it.key in cameraScreenViewModel.REQUIRED_PERMISSIONS && !it.value) {
                    permissionGranted = false
                }
            }
            cameraScreenViewModel.setPermissionState(permissionGranted)
        },
    )

    if (permissionState.value) {
        CameraView(imageCapture = cameraScreenViewModel.imageCapture.value, onImageCaptured = onImageCaptured)
    } else {
        LaunchedEffect(key1 = true) {
            resultLauncher.launch(cameraScreenViewModel.REQUIRED_PERMISSIONS)
        }
    }
}

@Composable
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
fun CameraView(
    imageCapture: ImageCapture,
    onImageCaptured: (Uri) -> Unit,
) {
    val context = LocalContext.current

    fun takePhoto(
        onImageCaptured: (Uri) -> Unit,
    ) {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture

        // Create time stamped name and MediaStore entry.
        val name = DateFormat.getDateTimeInstance()
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues,
            )
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val msg = "Photo capture succeeded: ${output.savedUri}"
                    // Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    Log.d(ContentValues.TAG, msg)
                    onImageCaptured(output.savedUri!!)
                }

                override fun onError(exception: ImageCaptureException) {
                }
            },
        )
    }

    AndroidView(
        { context ->
            val previewView = PreviewView(context).also {
                it.scaleType = PreviewView.ScaleType.FILL_CENTER
            }

            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        context as ComponentActivity,
                        cameraSelector,
                        preview,
                        imageCapture,
                    )
                } catch (ex: Exception) {
                    Log.e("Camera", "Use case binding failed", ex)
                }
            }, ContextCompat.getMainExecutor(context))
            previewView
        },
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    )
    Row(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(onClick = { takePhoto(onImageCaptured = onImageCaptured) }, Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Filled.CameraAlt,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(32.dp),
            )
        }
    }
}
