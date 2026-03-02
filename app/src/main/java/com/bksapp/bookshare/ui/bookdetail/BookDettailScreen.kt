package com.bksapp.bookshare.ui.bookdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.NetworkStatus
import com.bksapp.bookshare.ui.dashboard.HomeViewModel
import com.bksapp.bookshare.utils.ImageLoader


@Composable
fun BookDetails(bookID : Int){
    val bookDetailViewModel = hiltViewModel<BookDetailViewModel>()
    val bookState = bookDetailViewModel.bookState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {

        bookDetailViewModel.getBook(bookID)
    }

    Box(modifier = Modifier
        .fillMaxSize()){

        if(bookState.value == NetworkStatus.Loading){

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Green,
                strokeWidth = 4.dp
            )
        }
        else if(bookState.value is NetworkStatus.Success){
           val book = bookState.value as NetworkStatus.Success<Book>
            BookView(book.data)
        }
    }


}

@Composable
fun BookView(book : Book){
    Box() {

        Card(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
            shape = RectangleShape
        ) {
            ImageLoader(book.cover)
        }

        Row(modifier = Modifier
            .wrapContentWidth().align(Alignment.BottomEnd).padding(4.dp)) {
            Text(text = book.title, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}