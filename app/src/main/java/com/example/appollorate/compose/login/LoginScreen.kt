package com.example.appollorate.compose.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.appollorate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),
        content = {
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(R.dimen.default_elevation),
                ),
                modifier = Modifier.size(width = 380.dp, height = 420.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(36.dp),
                ) {
                    //
                    Text(
                        text = "Log in",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 36.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text(text = "Gebruikersnaam") },
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text(text = "Wachtwoord") },
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        TextButton(
                            onClick = { onDismissRequest() },
                            shape = RoundedCornerShape(5.dp),
                        ) {
                            Text(
                                text = "Annuleer",
                                fontSize = 20.sp,
                            )
                        }
                        TextButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(5.dp),
                        ) {
                            Text(
                                text = "Bevestig",
                                fontSize = 20.sp,
                            )
                        }
                    }
                }
            }
        },
    )
}
