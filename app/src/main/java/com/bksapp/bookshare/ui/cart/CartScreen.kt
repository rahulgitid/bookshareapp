package com.bksapp.bookshare.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.R
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.ui.theme.PrimaryLight
import com.bksapp.bookshare.utils.BookTopBar
import com.bksapp.bookshare.utils.ImageLoader

@Composable
fun CartScreen(
    cartViewModel: CartViewModel = hiltViewModel(),
    goback: () -> Unit,
) {

    val cartState by cartViewModel.cartState.collectAsStateWithLifecycle()
    Column {
        BookTopBar(title= "Cart",
            backButton = true,
            backAction = goback)
        LazyColumn {
            items(cartState, key = { it.id }, contentType = {}) { book ->
                CartItem(book)
            }
        }
    }
}


@Composable
fun CartItem(book: Book) {
    Card(modifier = Modifier
        .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryLight
        )) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Card(
                    modifier = Modifier
                        .size(80.dp, 100.dp)
                        .align(Alignment.CenterVertically),
                ) {
                    ImageLoader(book.cover, false, FilterQuality.Low)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(start = 8.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Text(modifier = Modifier
                                .weight(1f),
                                text = book.title,
                                style = MaterialTheme.typography.titleMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            IconButton(modifier = Modifier
                                .size(18.dp),
                                onClick = {}) { Icon(Icons.Filled.Delete, "delete") }
                        }
                        Spacer(modifier= Modifier.height(4.dp))
                        Text(
                            book.author,
                            style = MaterialTheme.typography.labelMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(modifier = Modifier.align(Alignment.BottomStart),
                        text = stringResource(R.string.rupee, book.price),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
