package com.bksapp.bookshare.ui.bookdetail

import android.view.WindowInsets
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.bksapp.bookshare.utils.cardStyle
import com.bksapp.bookshare.utils.roundedCard


@Composable
fun BookDetails(bookID : Int){
    val bookDetailViewModel = hiltViewModel<BookDetailViewModel>()
    val bookState = bookDetailViewModel.bookState.collectAsStateWithLifecycle()
    var favoriteState  by remember { mutableStateOf(false)}
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
            BookView(book.data,favoriteState,{favoriteState = favoriteState.not()})
        }
    }


}

@Composable
fun BookView(book : Book, favorite: Boolean, clickFavorite:()->Unit)
{
    Row() {
        Box(
            modifier = Modifier
                .fillMaxWidth().padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            ElevatedCard(
                modifier = Modifier.size(200.dp, 300.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(30.dp)
            ) {
                ImageLoader(book.cover)
            }
            Column(modifier = Modifier.align(Alignment.TopEnd)) {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.IosShare, contentDescription = "")
                }
                if(favorite) {
                    IconButton(onClick = clickFavorite) {
                        Icon(Icons.Filled.Favorite, contentDescription = "")
                    }
                }

                else{
                    IconButton(onClick = clickFavorite) {
                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "")
                    }
                }
            }
        }

    }

}
