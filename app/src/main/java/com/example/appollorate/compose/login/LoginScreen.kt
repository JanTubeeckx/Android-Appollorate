package com.example.appollorate.compose.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appollorate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
    onDismissRequest: () -> Unit,
) {
    val loginState by loginViewModel.uiState.collectAsState()

    val trailingIcon = @Composable {
        IconButton(onClick = { loginViewModel.showPassword() }) {
            Icon(
                if (loginState.isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }

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
                modifier = Modifier.size(width = 380.dp, height = 400.dp),
            ) {
                if (loginViewModel.loginApiState == LoginApiState.Loading && loginState.loggingIn) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                    ) {
                        Text(
                            text = "Een ogenblik geduld, u wordt ingelogd",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator(
                            modifier = Modifier.width(40.dp),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                } else if (loginViewModel.loginApiState == LoginApiState.Error) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(24.dp),
                    ) {
                        Text(
                            text = "Inloggen mislukt",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(36.dp))
                        Text(
                            text = "De ingevoerde inloggegevens zijn niet correct.",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Probeer het opnieuw.",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(36.dp))
                        TextButton(
                            onClick = {
                                loginViewModel.retryLogin()
                            },
                            shape = RoundedCornerShape(5.dp),
                        ) {
                            Text(
                                text = "Log opnieuw in",
                                fontSize = 20.sp,
                            )
                        }
                    }
                } else if (!loginState.loggingIn || loginViewModel.loginApiState == LoginApiState.Error) {
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
                            value = loginState.email,
                            onValueChange = {
                                loginViewModel.setEmail(it)
                            },
                            label = { Text(text = "Email") },
                        )
                        OutlinedTextField(
                            value = loginState.password,
                            onValueChange = {
                                loginViewModel.setPassword(it)
                            },
                            trailingIcon = trailingIcon,
                            label = { Text(text = "Wachtwoord") },
                            visualTransformation = if (loginState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        )
                        Spacer(modifier = Modifier.height(36.dp))
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            TextButton(
                                onClick = { loginViewModel.cancel() },
                                shape = RoundedCornerShape(5.dp),
                            ) {
                                Text(
                                    text = stringResource(R.string.CANCEL),
                                    fontSize = 20.sp,
                                )
                            }
                            TextButton(
                                onClick = {
                                    loginViewModel.setLoggingIn()
                                    loginViewModel.loginUser()
                                    if (loginViewModel.loginApiState == LoginApiState.Success) {
                                        onDismissRequest()
                                    }
                                },
                                shape = RoundedCornerShape(5.dp),
                            ) {
                                Text(
                                    text = stringResource(R.string.CONFIRM),
                                    fontSize = 20.sp,
                                )
                            }
                        }
                    }
                }
            }
        },
    )
}
