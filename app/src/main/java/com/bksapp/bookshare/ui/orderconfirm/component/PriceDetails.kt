package com.bksapp.bookshare.ui.orderconfirm.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.R

@Composable
fun PriceDetails(getSubTotal : ()->Int,getShipping:()->Int){

    Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)){

            Text(text = "Price Details",
                 style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold)
            ItemSpacer()
            Row{
                Text(modifier = Modifier
                    .weight(1f),
                    text = "SubTotal",
                    style = MaterialTheme.typography.titleSmall)
                Text(text = stringResource(R.string.rupee,getSubTotal()),
                    style = MaterialTheme.typography.titleSmall)

            }
            Spacer(modifier = Modifier.height(4.dp))
            Row{
                Text(modifier = Modifier
                    .weight(1f),
                    text = "Shipping",
                    style = MaterialTheme.typography.titleSmall)
                Text(
                    text = stringResource(R.string.rupee,getShipping()),
                    style = MaterialTheme.typography.titleSmall)

            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth().height(2.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Row{
                Text(modifier = Modifier
                    .weight(1f),
                    text = "Order Total",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.rupee,getSubTotal()+getShipping()),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold)

            }

        }
    }
}