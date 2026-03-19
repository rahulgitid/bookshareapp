package com.bksapp.bookshare.ui.dashboard.homecomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.R
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.local.entity.BookList
import com.bksapp.bookshare.ui.theme.Primary
import com.bksapp.bookshare.utils.ImageLoader
import com.bksapp.bookshare.utils.RatingBar

@Composable
fun BestSellerBooks(list: BookList, clickEvent: (Int)->Unit){
    val books = list.items
    if(books.isEmpty())return
    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .padding(16.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Primary, Color.White, Primary)
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "Best Seller", style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = "Best Seller of the month",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = ""
                    )
                }
            }
        }
        Column {
            Spacer(modifier = Modifier.height(100.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(books, key = { it.id }, contentType = { "seller_book" }) { book ->
                    BestSellerComponent(book,clickEvent)
                }
            }
        }
    }
}


@Composable
fun BestSellerComponent(book: Book,clickEvent: (Int)->Unit){

    BestSellerItem(book,clickEvent)

}


@Composable
fun BestSellerItem(book: Book,clickEvent: (Int)->Unit){
    val width = LocalWindowInfo.current.containerDpSize.width
    Card(onClick = {clickEvent(book.id)},
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Column(modifier = Modifier
            .width(width/2-width/8)
            .padding(12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                ImageLoader(book.cover, false, FilterQuality.Low)
            }
            Spacer(modifier= Modifier.height(8.dp))
            RatingBar(book.rating)
            Spacer(modifier= Modifier.height(4.dp))
            Text(text = book.title, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = book.author, style = MaterialTheme.typography.titleSmall,maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = stringResource(R.string.rupee,book.price))

        }
    }
}