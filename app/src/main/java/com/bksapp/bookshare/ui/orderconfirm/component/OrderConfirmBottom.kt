package com.bksapp.bookshare.ui.orderconfirm.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.R
import com.bksapp.bookshare.ui.bookdetail.component.Button
import com.bksapp.bookshare.ui.cart.CartViewModel

@Composable
fun OrderConfirmBottom(){
    Card(colors = CardDefaults.cardColors(Color.White)) {
        Box(modifier = Modifier
            .fillMaxWidth().padding(10.dp)) {
            Column {
                Text(text = "Total", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = stringResource(R.string.rupee,2500),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold)
            }
            Button(
                modifier = Modifier.size(130.dp,50.dp).align(Alignment.CenterEnd),
                text = "Place Order"){}

        }
    }
}