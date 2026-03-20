package com.bksapp.bookshare.ui.bookdetail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.utils.localBook

@Composable
fun BookNameText(){
    val book = localBook.current
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        textAlign = TextAlign.Center,
        text = book.title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}