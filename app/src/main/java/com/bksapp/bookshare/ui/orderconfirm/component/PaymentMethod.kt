package com.bksapp.bookshare.ui.orderconfirm.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.ui.theme.Primary
import com.bksapp.bookshare.ui.theme.PrimaryLight


@Composable
fun PaymentMethod() {

    var isSelected by remember { mutableIntStateOf(0) }
    Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth().padding(10.dp)
        ) {
            Text(text = "Payment Method",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold)
            ItemSpacer()
            Card(modifier = Modifier,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Primary),
                colors = CardDefaults.cardColors(containerColor =
                    if(isSelected==1){ PrimaryLight }
                    else{Color.White}),
                onClick = {isSelected = 1}) {
                Row(modifier = Modifier
                    .height(50.dp)
                    .padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Filled.Payment,
                        contentDescription = "CCAvenue",
                        Modifier.size(24.dp),
                        tint = Primary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(modifier = Modifier
                        .weight(1f),
                        text = "CCAvenue",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isSelected == 1) Color.White else Color.Black)
                    if (isSelected == 1)Icon(imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Check_It",
                        Modifier.size(32.dp).padding(end = 8.dp),
                        tint =  Color.White )
                }
            }
            Spacer(modifier = Modifier
                .height(8.dp))
            Card(modifier = Modifier,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Primary),
                colors = CardDefaults.cardColors(containerColor =
                    if(isSelected==2){ PrimaryLight }
                    else{Color.White}),
                onClick = {isSelected = 2}) {
                Row(modifier = Modifier
                    .height(50.dp)
                    .padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Payment,
                        contentDescription = "CCAvenue",
                        Modifier.size(24.dp),
                        tint = Primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(modifier = Modifier
                        .weight(1f),
                        text = "Razorpay",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isSelected == 2) Color.White else Color.Black )
                    if (isSelected == 2)Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Check_It",
                        Modifier.size(32.dp).padding(end = 8.dp),
                        tint = Color.White)
                }
            }
        }
    }
}