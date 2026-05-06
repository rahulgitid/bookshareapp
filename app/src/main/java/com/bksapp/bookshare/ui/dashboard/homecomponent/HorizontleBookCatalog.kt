package com.bksapp.bookshare.ui.dashboard.homecomponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.local.entity.BookList
import com.bksapp.bookshare.ui.theme.AppTypography
import com.bksapp.bookshare.ui.theme.Primary
import com.bksapp.bookshare.ui.theme.PrimaryLight
import com.bksapp.bookshare.utils.ImageLoader
import kotlin.div

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalBookCatalog(list: BookList, showBookDetail : (id : Int)->Unit){
    val books = list.items
    val customState = rememberLazyGridState()
    val width = LocalWindowInfo.current.containerDpSize.width
    val bookItemWidth = width-width/3-width/10
    val itemImageBoxWidth = bookItemWidth/2
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
            key = { _, item -> item.id }) { index, book ->
            BookDesign(bookItemWidth,itemImageBoxWidth,index, book, showBookDetail)
        }
    }
}


}

@Composable
fun BookDesign(bookItemWidth: Dp, itemImageBoxWidth:Dp, bookNo: Int, book: Book, showBookDetail : (id : Int)->Unit){

    Card(
        modifier = Modifier.width(bookItemWidth).wrapContentHeight(),
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
                .width(itemImageBoxWidth)
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