package com.bksapp.bookshare.ui.dashboard.homecomponent

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.ui.theme.AppTypography
import com.bksapp.bookshare.utils.ImageLoader
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("FrequentlyChangingValue")
@Composable
fun AutoScrollRow(books: List<Book>, callBookCat: (String) -> Unit) {
    val config = LocalWindowInfo.current
    val screenWidth = config.containerDpSize.width
    val itemWidth = screenWidth * 0.7f
    val contentPadding = PaddingValues(horizontal = (screenWidth - itemWidth) / 2)


    val totalPages = Int.MAX_VALUE // 1. Use max value for "infinite" swiping
    val actualSize = books.size


     val pagerState = rememberPagerState(
        initialPage = (totalPages / 2) - ((totalPages / 2) % actualSize), // Ensures it starts at index 0
        pageCount = { totalPages }
    )

   val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(Unit) {

        snapshotFlow { isDragged }
            .collectLatest {
                if (!it) {
                    while (true) {
                        delay(2000L)
                        with(pagerState) {
                            val nextPage = (currentPage + 1) % pageCount
                            animateScrollToPage(nextPage)
                        }
                    }
                }
            }
    }
    val currentIndex by remember {
        derivedStateOf { pagerState.currentPage % actualSize }
    }

    Spacer(modifier = Modifier.height(12.dp))
    Box {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(itemWidth),
            contentPadding = contentPadding,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->
            val index = page % actualSize
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                                ).absoluteValue
                        // Zoom effect: Scale from 0.8 to 1.0 based on distance from center
                        val scale = lerp(
                            start = 0.8f,
                            stop = 1.0f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleX = scale
                        scaleY = scale
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1.0f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxWidth()
                    .aspectRatio(1f)) {
                TopScrollRowItem({ books[index] }, callBookCat)
            }


        }
        PagerDots(Modifier.align(Alignment.BottomCenter), actualSize, { currentIndex })
    }

}

@Composable
fun PagerDots(modifier: Modifier, actualSize: Int, currentIndex: () -> Int) {


    Row(
        modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    )
    {
        repeat(actualSize) { iteration ->
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .drawBehind {
                        val isSelected = currentIndex() == iteration
                        drawRect(if (isSelected) Color.Red else Color.LightGray)
                    }
                    .width(15.dp)
                    .height(6.dp)
            )
        }
    }

}


@Composable
fun TopScrollRowItem(getBook: () -> Book, callBookCat: (String) -> Unit) {

    val book by remember(getBook) { derivedStateOf { getBook() } }
    Card(
        onClick = { callBookCat(book.category) }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                ImageLoader(book.cover, false, FilterQuality.Low)
            }
            Column(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(4.dp),
                    text = book.title,
                    style = AppTypography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(4.dp),
                    text = "${book.price}Rs",
                    style = AppTypography.titleSmall,
                    textAlign = TextAlign.End
                )
            }

        }
    }
}