package com.bksapp.bookshare.ui.bookdetail.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.utils.ImageLoader
import com.bksapp.bookshare.utils.localBook

@Composable
fun TopBookPreView(){
    val book = localBook.current
    ElevatedCard(
        modifier = Modifier.size(200.dp, 300.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(30.dp),
    ) {
        ImageLoader(book.cover, quality = FilterQuality.Medium)
    }
}