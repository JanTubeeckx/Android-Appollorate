package com.example.appollorate.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appollorate.R
import com.example.appollorate.ui.theme.AppollorateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 60.dp, horizontal = 5.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.book_open_solid),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 8.dp, top = 13.dp, end = 12.dp, bottom = 8.dp),
            )
            Text(
                text = stringResource(R.string.APP_NAME),
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        ElevatedCard(
            onClick = { /*TODO*/ },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier.size(width = 320.dp, height = 240.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight().padding(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Assignment,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().size(75.dp),
                )
                Text(
                    text = stringResource(R.string.DAMAGE_REGISTRATION),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            ElevatedCard(
                onClick = { /*TODO*/ },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.size(width = 150.dp, height = 120.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight().padding(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().size(55.dp),
                    )
                    Text(
                        text = stringResource(R.string.INVENTORIES),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            ElevatedCard(
                onClick = { /*TODO*/ },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.size(width = 150.dp, height = 120.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight().padding(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().size(55.dp).padding(5.dp),
                    )
                    Text(
                        text = stringResource(R.string.APP_NAME),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppollorateTheme {
        HomeScreen()
    }
}
