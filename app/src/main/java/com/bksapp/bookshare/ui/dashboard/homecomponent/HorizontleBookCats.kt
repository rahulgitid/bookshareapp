package com.bksapp.bookshare.ui.dashboard.homecomponent

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BookCats(cats: List<String>,onClick:(Int)->Unit){
    LazyRow(
        contentPadding = PaddingValues(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(cats, key={it},contentType = {"Categories"}){
            BookCatItem(it,onClick)
        }
    }

}

@Composable
fun BookCatItem(cat: String,onClick:(Int)->Unit){
    Card(modifier = Modifier
        .wrapContentWidth()
        .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {onClick(1)}
    ) {
        Text(modifier = Modifier.padding(10.dp), text = cat, style = MaterialTheme.typography.bodyMedium)
    }
}