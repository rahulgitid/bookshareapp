package com.bksapp.bookshare.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.R
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.NetworkStatus


@Composable
fun HomeScreen(){

     val homeViewModel = hiltViewModel<HomeViewModel>()
     val bookState = homeViewModel.bookState.collectAsStateWithLifecycle()
     val configuration = LocalWindowInfo.current.containerSize

    when(bookState.value){
        is NetworkStatus.Idle->{}
        is NetworkStatus.Loading->{}
        is NetworkStatus.Success->{}
        is NetworkStatus.Error->{}
    }

   Box(modifier = Modifier
       .fillMaxSize()
       .background(
           brush = Brush.radialGradient(
               colors = listOf(Color.Cyan, Color.Green, Color.Cyan),
               center = Offset(configuration.width / 2f, configuration.height / 2f),
               radius = configuration.width.toFloat(),
               tileMode = TileMode.Clamp
           )
       )
   ){

       if(bookState.value is NetworkStatus.Success){
           val books = bookState.value as NetworkStatus.Success<List<Book>>
           BookList(books.data)
       }
       else if(bookState.value == NetworkStatus.Loading){
           Column(modifier = Modifier
               .fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
           {

               CircularProgressIndicator(
                   color = Color.Red,
                   strokeWidth = 10.dp
               )

           }
       }
   }

}

@Composable
fun BookList(books: List<Book>){
    LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
        item(span = { GridItemSpan(maxLineSpan) }){
            Text(text= "Books")
        }
           items(items = books,key = {book->book.id}){ book->
               BookDesign(book)
           }
        }


}


@Composable
fun BookDesign(book: Book){

    Card {
        Column {
            Image(modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Book",
                contentScale = ContentScale.FillWidth)
            Column(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(listOf(Color.LightGray,Color.Cyan))
                )){
                Text(modifier = Modifier.fillMaxWidth().padding(4.dp),
                    text = book.title, maxLines = 2, color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Text(modifier = Modifier.fillMaxWidth().padding(4.dp),
                        text = "${book.price}Rs", color = Color.Black, textAlign = TextAlign.End)
            }

        }
    }

}