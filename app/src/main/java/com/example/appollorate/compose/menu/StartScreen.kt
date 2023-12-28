package com.example.appollorate.compose.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
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
fun StartScreen(
    goToIdentification: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        ElevatedCard(
            onClick = goToIdentification,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(R.dimen.default_elevation),
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
            modifier = Modifier
                .size(width = 370.dp, height = 160.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight().padding(16.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.address_card_solid),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth().size(53.dp),
                )
                Text(
                    text = stringResource(R.string.IDENTIFICATION),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCard(
            onClick = { /*TODO*/ },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(R.dimen.default_elevation),
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
            modifier = Modifier.size(width = 370.dp, height = 160.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight().padding(16.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.box_solid),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth().size(53.dp),
                )
                Text(
                    text = stringResource(R.string.PROTECTION),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCard(
            onClick = { /*TODO*/ },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(R.dimen.default_elevation),
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
            modifier = Modifier.size(width = 370.dp, height = 160.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight().padding(16.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.file_pen_solid),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth().size(53.dp),
                )
                Text(
                    text = stringResource(R.string.SHAPE_AND_DAMAGE),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    AppollorateTheme {
        StartScreen(goToIdentification = {})
    }
}
