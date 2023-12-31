package com.example.appollorate.compose.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appollorate.R
import com.example.appollorate.compose.inventoryfield.InventoryField
import com.example.appollorate.ui.theme.AppollorateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    inventoryScreenViewModel: InventoryScreenViewModel = viewModel(factory = InventoryScreenViewModel.Factory),
) {
    val inventoryFieldListState by inventoryScreenViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            state = lazyListState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            items(inventoryFieldListState.inventoryFieldList, key = { i -> i.id }) {
                InventoryField(inventoryField = it)
            }
        }
        ElevatedCard(
            onClick = { /*TODO*/ },
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
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(text = stringResource(R.string.NEXT))
        }
    }
}

@Preview
@Composable
fun IdentificationScreenPreview() {
    AppollorateTheme {
        InventoryScreen()
    }
}
