package com.example.appollorate.compose.formcharacteristics.cover

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appollorate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoverFormChars(
    coverFormCharsViewModel: CoverFormCharsViewModel = viewModel(factory = CoverFormCharsViewModel.Factory),
    navController: NavController,
) {
    val coverFormCharsState by coverFormCharsViewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        println(coverFormCharsState.coverFormCharInventorySteps)
        items(coverFormCharsState.coverFormCharInventorySteps, key = { s -> s.id }) {
            ElevatedCard(
                onClick = {
                    when (it.description) {
                        // COVER -> navController.navigate("Cover/${it.id}")
                        // PROTECTION -> navController.navigate("Protection/${it.id}")
                    }
                },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.surfaceTint,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(R.dimen.default_elevation),
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
                modifier = Modifier
                    .size(width = 370.dp, height = 113.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    AsyncImage(
                        model = "https://csb100320019d6bcc0d.blob.core.windows.net/thumbnails/" + "${it.icon}",
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Text(
                        text = it.description,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}
