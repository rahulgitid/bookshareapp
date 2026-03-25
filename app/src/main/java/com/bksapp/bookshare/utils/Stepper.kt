package com.bksapp.bookshare.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Stepper(plus:()->Unit,minus:()->Unit,qunatity:()->Int){
    Card {

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(modifier = Modifier
                .width(34.dp)
                .background(color = Color.Black)
                .clickable(onClick = plus ),
                textAlign = TextAlign.Center,
                color = Color.White,
                text= "+")
            VerticalDivider(Modifier.height( 8.dp))
            Text(modifier = Modifier
                .width(34.dp)
                .background(color = Color.White),
                textAlign = TextAlign.Center,
                text = "${qunatity()}")
            VerticalDivider(Modifier.height( 8.dp))
            Text(modifier = Modifier
                .width(34.dp)
                .background(color = Color.Red)
                .clickable(onClick = minus),
                color = Color.White,
                textAlign = TextAlign.Center,
                text = "-")
        }
    }
}