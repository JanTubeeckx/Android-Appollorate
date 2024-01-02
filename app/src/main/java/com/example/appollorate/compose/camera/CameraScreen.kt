package com.example.appollorate.compose.camera

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
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
import com.example.appollorate.ui.theme.AppollorateTheme

@Composable
fun CameraScreen(
    cameraScreenViewModel: CameraScreenViewModel = viewModel(),
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
/*            if (!permissionGranted) {
                Toast.makeText(
                    baseContext,
                    "Permission request denied",
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                startCamera()
            }*/
        },
    )

    if (permissionState.value) {
        cameraView(imageCapture = cameraScreenViewModel.imageCapture.value)
    } else {
        LaunchedEffect(key1 = true) {
            resultLauncher.launch(cameraScreenViewModel.REQUIRED_PERMISSIONS)
        }
    }
}

@Composable
fun cameraView(
    imageCapture: ImageCapture,
) {
    AndroidView({ context ->
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
    }, modifier = Modifier.fillMaxHeight().fillMaxWidth())

    var context = LocalContext.current
    Row(
        Modifier.fillMaxHeight().fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(onClick = { }, Modifier.padding(16.dp)) {
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

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun CameraScreenPreview() {
    AppollorateTheme {
        CameraScreen()
    }
}
