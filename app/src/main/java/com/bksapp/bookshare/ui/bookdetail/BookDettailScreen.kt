package com.bksapp.bookshare.ui.bookdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.NetworkStatus
import com.bksapp.bookshare.ui.bookdetail.component.BookDetail
import com.bksapp.bookshare.ui.bookdetail.component.BookNameText
import com.bksapp.bookshare.ui.bookdetail.component.BookOverView
import com.bksapp.bookshare.ui.bookdetail.component.BookPrice
import com.bksapp.bookshare.ui.bookdetail.component.Favorite
import com.bksapp.bookshare.ui.bookdetail.component.ShareBook
import com.bksapp.bookshare.ui.bookdetail.component.TopBookPreView
import com.bksapp.bookshare.utils.localBook


@Composable
fun BookDetails(bookID: Int) {
    val bookDetailViewModel = hiltViewModel<BookDetailViewModel>()
    val bookState: NetworkStatus<Book> by bookDetailViewModel.bookState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        bookDetailViewModel.getBook(bookID)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (bookState == NetworkStatus.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Green,
                strokeWidth = 4.dp
            )
        } else if (bookState is NetworkStatus.Success) {
            val book = (bookState as NetworkStatus.Success<Book>).data
            CompositionLocalProvider(localBook provides book) {
                BookView()
            }
        }
    }
}



@Composable
fun BookView() {
    Column(modifier = Modifier.padding(20.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TopBookPreView()
            Column(modifier = Modifier.align(Alignment.TopEnd)) {
                ShareBook()
                Favorite()
            }
        }

        BookNameText()
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(IntrinsicSize.Max)
                .padding(top = 4.dp)
        ) {
            BookDetail()
        }

        BookPrice()
        Spacer(Modifier.height(14.dp))
        BookOverView()
    }
}







