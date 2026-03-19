package com.bksapp.bookshare.ui.dashboard.homecomponent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.data.local.entity.BookList

@Composable
fun SuccessContent(
    bookCats: List<String>,
    carouselData: BookList,
    allBooksData: BookList,
    bestSellerDataData:BookList,
    showBookDetail: (id: Int) -> Unit,
    callBookCat: (String) -> Unit
) {

    val listState = rememberLazyListState()
    LazyColumn(
        state = listState
    ) {
        item(key = "categories") {
            BookCats(bookCats,showBookDetail)
        }

        item(key = "carousel") {
            AutoScrollRow(carouselData, callBookCat)
        }

        item(key = "space") {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item(key = "catalog") {
            HorizontalBookCatalog(allBooksData, showBookDetail)
        }

        item(key = "bestseller") {
            BestSellerBooks(bestSellerDataData, showBookDetail)

        }
    }


}