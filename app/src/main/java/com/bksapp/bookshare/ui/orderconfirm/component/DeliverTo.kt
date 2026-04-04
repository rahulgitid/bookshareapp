package com.bksapp.bookshare.ui.orderconfirm.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.ui.address.Address
import com.bksapp.bookshare.ui.theme.Primary

@Composable
fun DeliveryTo(address: Address, goToAddress: ()->Unit){

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
                        text = address.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    val addr = "${address.address}, ${address.landMark}\n${address.city}, ${address.state}, ${address.zipCode}"
                    Text(text = if(address.address.isNotBlank())addr.trim() else "", style = MaterialTheme.typography.titleSmall)
                }

                Text(modifier = Modifier
                    .clickable(onClick = goToAddress),
                    text = "Change",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Blue
                )
            }
        }
    }
}

