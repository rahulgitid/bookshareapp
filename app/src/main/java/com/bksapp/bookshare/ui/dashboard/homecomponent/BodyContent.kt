package com.bksapp.bookshare.ui.dashboard.homecomponent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.data.local.entity.BookList

data class ContentType(val id: Int, val type: Int)
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

    val list = mutableSetOf<ContentType>()
    (0..4).forEach {
        list.add(ContentType(it,it))
    }

    LazyColumn(state = listState) {

        items(list.size, key = {it}, contentType = {it}){type->

            when(type){
                0-> BookCats(bookCats,showBookDetail)
                1-> AutoScrollRow(carouselData, callBookCat)
                2-> Spacer(modifier = Modifier.height(16.dp))
                3-> HorizontalBookCatalog(allBooksData, showBookDetail)
                4-> BestSellerBooks(bestSellerDataData, showBookDetail)
            }
        }
        /*item { BookCats(bookCats,showBookDetail)}
         item {
            AutoScrollRow(carouselData, callBookCat)
        }
       item { Spacer(modifier = Modifier.height(16.dp))}

            item { HorizontalBookCatalog(allBooksData, showBookDetail) }

      item {  BestSellerBooks(bestSellerDataData, showBookDetail) }*/
    }

}

