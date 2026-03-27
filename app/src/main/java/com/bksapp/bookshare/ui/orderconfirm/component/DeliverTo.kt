package com.bksapp.bookshare.ui.orderconfirm.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.R
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.ui.theme.Primary
import com.bksapp.bookshare.ui.theme.PrimaryLight
import com.bksapp.bookshare.utils.ImageLoader
import com.bksapp.bookshare.utils.Stepper

@Composable
fun DeliveryTo(){

    Card(
        modifier = Modifier.padding(top = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier
            .padding(10.dp)) {
            Text(
                text = "Deliver To",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            ItemSpacer()

            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {

                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "location_icon",
                    Modifier.size(20.dp),
                    tint = Primary
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Name",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Address", style = MaterialTheme.typography.titleSmall)
                }

                Text(
                    text = "Change",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Blue
                )
            }
        }
    }
}

