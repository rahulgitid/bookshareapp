package com.bksapp.bookshare.ui.address.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun DefaultAddress(checkedChange :(checked: Boolean)-> Unit){

    var toggleState by remember { mutableStateOf(false) }
    Card {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {

            Column(modifier = Modifier
                .weight(1f)) {

                Text(text = "Set as Default Address",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier
                    .height(2.dp))
                Text(text = "Use this address as your delivery address",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Checkbox(
                onCheckedChange = {
                                     toggleState = it
                                     checkedChange(toggleState)
                                  },
                                  checked = toggleState)
        }


    }
}