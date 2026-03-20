package com.bksapp.bookshare.ui.bookdetail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.R
import com.bksapp.bookshare.utils.localBook

@Composable
fun BookPrice() {
    val book = localBook.current
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        text = stringResource(R.string.rupee, book.price),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}