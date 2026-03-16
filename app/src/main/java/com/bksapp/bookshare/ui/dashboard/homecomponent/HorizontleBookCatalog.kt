package com.bksapp.bookshare.ui.dashboard.homecomponent

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridPrefetchStrategy
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.ui.theme.AppTypography
import com.bksapp.bookshare.ui.theme.Primary
import com.bksapp.bookshare.ui.theme.PrimaryLight
import com.bksapp.bookshare.utils.ImageLoader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalBookCatalog(books: List<Book>,showBookDetail : (id : Int)->Unit){

    val customState = rememberLazyGridState()
    Column(modifier = Modifier
        .background(
            brush = Brush.verticalGradient(
                listOf(Primary, Color.White, PrimaryLight)
            ),
            shape = RoundedCornerShape(8.dp)
        ) ){
    BookSectionHeader("Now Trending")
    LazyHorizontalGrid(
        modifier = Modifier.height(360.dp),
        rows = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        state = customState

    )
    {
        itemsIndexed(
            items = books,
            key = { _, item -> item.id },
            contentType = { _, _ -> "book_content" }) { index, book ->
            BookDesign(index, book, showBookDetail)
        }
    }
}


}

@Composable
fun BookDesign(bookNo: Int, book: Book,showBookDetail : (id : Int)->Unit){
    val width = LocalWindowInfo.current.containerDpSize.width
    Card(
        modifier = Modifier.width(width-width/3-width/10).wrapContentHeight(),
        onClick =  { showBookDetail(book.id) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row {
            Text(modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically),
                text = "${bookNo+1}",
                style = MaterialTheme.typography.displaySmall,
                fontFamily = FontFamily.SansSerif)
            Box(modifier = Modifier
                .width(width/4)
                .height(150.dp)){

                ImageLoader(book.cover,false, FilterQuality.Low)
            }
            Column(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
            ){
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                    text = book.title, style = AppTypography.titleSmall,maxLines = 2, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold)
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                    text = "${book.price}Rs", style = AppTypography.titleSmall
                )
            }

        }
    }


}