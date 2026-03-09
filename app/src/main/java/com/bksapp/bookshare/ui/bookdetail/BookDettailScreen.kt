package com.bksapp.bookshare.ui.bookdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.NetworkStatus
import com.bksapp.bookshare.utils.ImageLoader
import com.bksapp.bookshare.utils.localBook

@OptIn(ExperimentalMaterial3Api::class)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookView() {
    val book = localBook.current
    Column(modifier = Modifier.padding(20.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ElevatedCard(
                modifier = Modifier.size(200.dp, 300.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(30.dp),
            ) {
                ImageLoader(book.cover)
            }
            Column(modifier = Modifier.align(Alignment.TopEnd)) {
                ShareBook()
                Favorite()
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            textAlign = TextAlign.Center,
            text = book.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(IntrinsicSize.Max)
                .padding(top = 4.dp)
        ) {
            BookData()
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            text = "₹${book.price}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(14.dp))
        BottomSheetTrigger()
    }
}

@Composable
fun Favorite() {
    var isFavorite by remember { mutableStateOf(false) }
    IconButton(onClick = { isFavorite = isFavorite.not() }) {
        Icon(
            if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "",
            tint = Color.Red
        )
    }
}

@Composable
fun ShareBook() {
    IconButton(onClick = {}) {
        Icon(Icons.Filled.IosShare, contentDescription = "")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetTrigger() {
    var showBottomSheet by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clickable(onClick = { showBottomSheet = !showBottomSheet })
            .fillMaxWidth()
            .height(40.dp)
            .background(color = Color(0x92D7E8DC), shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 15.dp), text = "Book Overview"
        )
        Icon(
            modifier = Modifier.padding(end = 15.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = ""
        )
    }

    BottomSheetManager({ showBottomSheet }, { showBottomSheet = false })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetManager(isVisible: () -> Boolean, dismiss: () -> Unit) {
    if (isVisible()) {
        BookBottomSheet(dismiss)
    }
}

@Composable
fun BookData() {
    val book = localBook.current
    BookInfo("Published", book.published, Icons.Filled.CalendarMonth)
    VerticalDivider(
        modifier = Modifier.padding(6.dp),
        thickness = 2.dp,
        color = Color.LightGray
    )
    BookInfo("Pages", book.pages, Icons.AutoMirrored.Filled.MenuBook)
    VerticalDivider(
        modifier = Modifier.padding(6.dp),
        thickness = 2.dp,
        color = Color.LightGray
    )
    BookInfo("Reviews", book.reviews, Icons.Filled.Star)
}

@Composable
fun BookInfo(tag: String, data: String, icon: ImageVector) {
    Column(
        modifier = Modifier.padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            tag, style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Row() {
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp),
                imageVector = icon,
                contentDescription = tag
            )
            Text(data, style = MaterialTheme.typography.titleMedium)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookBottomSheet(dismiss: () -> Unit) {
    val state = rememberModalBottomSheetState()
    val windowHeight = LocalWindowInfo.current.containerDpSize.height
    val screenH = windowHeight - windowHeight / 3
    val scrollState = rememberScrollState()
    ModalBottomSheet(
        onDismissRequest = dismiss,
        sheetState = state
    ) {
        Column(
            modifier = Modifier
                .height(screenH)
                .verticalScroll(scrollState)
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Overview\n\nA book overview is a concise summary of a book's main points, covering the plot, characters, and setting for fiction, or key themes and arguments for non-fiction. It acts as a \"teaser\" helping readers decide whether to read the full text by distilling the author's work into a digestible format." +
                        "\n\nKey Elements of a Book Overview:\n\n" +
                        "Fiction: Typically includes the \"big five\"—main character, goal, obstacles, actions, and outcome.\n" +
                        "Non-Fiction: Focuses on the core argument, central themes, and supporting points, often with actionable insights.\n" +
                        "Purpose: Summaries are used for comprehension, quick learning, or to gauge interest without reading the entire book.\n" +
                        "Structure: Often presented as a brief narrative or through bulleted lists of key takeaways.\n" +
                        "Resources: Platforms like BlinkistShortform, and SparkNotes provide summaries to help readers quickly grasp the essence of books. " +
                        "An effective overview should focus on central messages rather than unnecessary details. "
            )
        }
    }
}
