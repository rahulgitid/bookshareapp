package com.bksapp.bookshare.ui.cart.cartComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.ui.bookdetail.component.Button
import com.bksapp.bookshare.ui.theme.Background
import com.bksapp.bookshare.ui.theme.Primary
import com.bksapp.bookshare.ui.theme.PrimaryLight

@Composable
fun CouponCode(){

    var couponCodeState by remember { mutableStateOf("") }
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .padding(8.dp),
        elevation = CardDefaults.cardElevation(20.dp),
        colors = CardDefaults.cardColors(containerColor = Background)) {

        Column(modifier = Modifier
            .fillMaxWidth().padding(12.dp)) {
            Text("Coupon Code",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold)
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)) {

                OutlinedTextField(
                    modifier = Modifier.height(50.dp)
                        .weight(1f),
                    value = couponCodeState,
                    onValueChange = {couponCodeState = it},
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor  = Primary,)
                )

                Button(modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .padding(start = 8.dp),
                    text="Apply",
                    btColor = MaterialTheme.colorScheme.primary,
                    clickAction = {})
            }
        }
    }
}