package com.bksapp.bookshare.ui.dashboard

import android.R.attr.textStyle
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.R
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.NetworkStatus
import com.bksapp.bookshare.ui.theme.AppTheme
import com.bksapp.bookshare.ui.theme.AppTypography

import com.bksapp.bookshare.utils.ImageLoader

@Composable
fun HomeScreen(showBookDetail : (book : Book)->Unit){

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
       /*.background(
           brush = Brush.radialGradient(
               colors = listOf(Color.Cyan, Color.Green, Color.Cyan),
               center = Offset(configuration.width / 2f, configuration.height / 2f),
               radius = configuration.width.toFloat(),
               tileMode = TileMode.Clamp
           )
       )*/
   ){

       if(bookState.value is NetworkStatus.Success){
           val books = bookState.value as NetworkStatus.Success<List<Book>>
           BookList(books.data,showBookDetail)
       }
       else if(bookState.value == NetworkStatus.Loading){
           Column(modifier = Modifier
               .fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally)
           {

               CircularProgressIndicator(

                   strokeWidth = 10.dp
               )

           }
       }
   }

}

@Composable
fun BookList(books: List<Book>,showBookDetail : (book : Book)->Unit){
    LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        item(span = { GridItemSpan(maxLineSpan) }){
            Text(text= "Books")
        }
           items(items = books,key = {book->book.id}){ book->
               BookDesign(book,showBookDetail)
           }
        }


}


@Composable
fun BookDesign(book: Book,showBookDetail : (book : Book)->Unit){

    Card(
        onClick = {
            showBookDetail(book)
        }
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(150.dp)){

                ImageLoader(book.cover,true)
            }
            Column(modifier = Modifier
                .fillMaxSize()
               ){
                Text(modifier = Modifier.fillMaxWidth().padding(4.dp),
                    text = book.title, style = AppTypography.titleMedium,maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(modifier = Modifier.fillMaxWidth().padding(4.dp),
                        text = "${book.price}Rs", style = AppTypography.titleSmall, textAlign = TextAlign.End
                 )
            }

        }
    }

}