package com.bksapp.bookshare.ui.dashboard

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridPrefetchStrategy
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.NetworkStatus
import com.bksapp.bookshare.ui.dashboard.homecomponent.AutoScrollRow
import com.bksapp.bookshare.ui.theme.AppTypography
import com.bksapp.bookshare.utils.ImageLoader
import kotlin.math.abs
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(showBookDetail : (book : Book)->Unit, callBookCat : (String)->Unit){

     val homeViewModel = hiltViewModel<HomeViewModel>()
     val bookState by homeViewModel.bookState.collectAsStateWithLifecycle()
     val bookCats by homeViewModel.bookCats.collectAsStateWithLifecycle()


    when(bookState){
        is NetworkStatus.Idle->{}
        is NetworkStatus.Loading->{}
        is NetworkStatus.Success->{}
        is NetworkStatus.Error->{}
    }

   Box(modifier = Modifier
       .fillMaxSize()
   ){

       if(bookState is NetworkStatus.Success){
           val books = bookState as NetworkStatus.Success<List<Book>>
           Column {
                 BookCats(bookCats)
                 AutoScrollRow(books.data.subList(0,7),callBookCat)
                 BookList(books.data, showBookDetail)

           }
       }
       else if(bookState == NetworkStatus.Loading){
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
fun BookCats(cats: List<String>){
    LazyRow(
        contentPadding = PaddingValues(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

         items(cats, contentType = {"Categories"}){
             BookCatItem(it)
         }
    }

}

@Composable
fun BookCatItem(cat: String){
    Card(modifier = Modifier
        .wrapContentWidth()
        .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Text(modifier = Modifier.padding(10.dp), text = cat, style = MaterialTheme.typography.bodyMedium)
    }
}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookList(books: List<Book>,showBookDetail : (book : Book)->Unit){
    val customState = rememberLazyGridState(
        prefetchStrategy = LazyGridPrefetchStrategy(nestedPrefetchItemCount = 6)
    )
    LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = customState

        ) {
        item(span = { GridItemSpan(maxLineSpan) }){
            Text(text= "Books")
        }
           items(items = books,key = {book->book.id}, contentType = {"book_item"}){ book->
               BookDesign(book,showBookDetail)
           }
        }


}


@Composable
fun BookDesign(book: Book,showBookDetail : (book : Book)->Unit){
    Card(
        onClick =  { showBookDetail(book) }
    ) {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)){

                ImageLoader(book.cover,false, FilterQuality.Low)
            }
            Column(modifier = Modifier
                .fillMaxSize()
               ){
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                    text = book.title, style = AppTypography.titleMedium,maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                        text = "${book.price}Rs", style = AppTypography.titleSmall, textAlign = TextAlign.End
                 )
            }

        }
    }

}

