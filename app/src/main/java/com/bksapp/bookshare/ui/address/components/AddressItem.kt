package com.bksapp.bookshare.ui.address.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.ui.address.Address
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun AddressItem(itemClicked:(address: Address)->Unit, address: Address){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 10.dp)
        .background(Color.White)){
        Card(modifier = Modifier
            .fillMaxWidth()
            .clickable {itemClicked(address)}
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(10.dp)
            )
        ) {
            Column(modifier = Modifier
                .padding(8.dp)){

                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(modifier = Modifier.size(24.dp), selected = true, onClick = {})
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = address.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    if (address.isDefault) {
                        ElevatedAssistChip(
                            modifier = Modifier.padding(2.dp),
                            label = { Text(text = "Default") },
                            onClick = {}
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${address.address},\n${address.city}, ${address.state}", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = address.zipCode, style = MaterialTheme.typography.titleSmall)

            }
        }
    }


}