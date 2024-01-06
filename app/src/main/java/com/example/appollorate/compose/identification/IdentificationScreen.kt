package com.example.appollorate.compose.identification

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.appollorate.R
import com.example.appollorate.compose.camera.CameraScreen
import com.example.appollorate.compose.inventoryfield.InventoryField
import com.example.appollorate.compose.inventoryfield.InventoryFieldViewModel
import com.example.appollorate.compose.utils.AppolloRateNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentificationScreen(
    navigationType: AppolloRateNavigationType,
    identificationScreenViewModel: IdentificationScreenViewModel = viewModel(factory = IdentificationScreenViewModel.Factory),
    inventoryFieldViewModel: InventoryFieldViewModel = viewModel(factory = InventoryFieldViewModel.Factory),
    showCamera: Boolean,
    openCamera: () -> Unit,
    closeCamera: () -> Unit,
    goToStartScreen: () -> Unit,
) {
    val identificationScreenState by identificationScreenViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    var showImage by remember { mutableStateOf(false) }
    var photoUri by remember { mutableStateOf(Uri.EMPTY) }

    fun handleImageCaptured(uri: Uri) {
        photoUri = uri
        showImage = true
    }

    if (!showCamera) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn(
                state = lazyListState,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = if (navigationType == AppolloRateNavigationType.BOTTOM_NAVIGATION) {
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                } else {
                    Modifier
                        .fillMaxWidth()
                        .padding(40.dp)
                },
            ) {
                items(identificationScreenState.inventoryFieldList, key = { i -> i.id }) {
                    InventoryField(inventoryField = it)
                }
            }
            ElevatedCard(
                onClick = { openCamera() },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(R.dimen.default_elevation),
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
                modifier = Modifier
                    .size(height = 95.dp, width = 375.dp),
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(36.dp),
                )
                Text(
                    text = "Voeg een foto van het boek toe",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            ElevatedButton(
                onClick = { goToStartScreen() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(text = stringResource(R.string.NEXT))
            }
        }
    } else if (showCamera && !showImage) {
        CameraScreen(onImageCaptured = ::handleImageCaptured)
    } else {
        Image(
            painter = rememberAsyncImagePainter("$photoUri"),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
        Row(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                onClick = {
                    closeCamera()
                    val result = identificationScreenViewModel.sendImage(photoUri)
                    inventoryFieldViewModel.setInput("imageLabel", result)
                },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.width(40.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(20.dp),
                )
            }
        }
        Row(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                openCamera()
                showImage = false
            }, Modifier.padding(16.dp)) {
                Text(
                    text = "Maak nieuwe foto",
                    fontSize = 18.sp,
                )
            }
        }
    }
}
