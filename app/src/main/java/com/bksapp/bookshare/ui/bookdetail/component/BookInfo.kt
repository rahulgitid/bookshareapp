package com.bksapp.bookshare.ui.bookdetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.utils.localBook

@Composable
fun BookDetail(){
    val book = localBook.current
    BookInfo("Published", book.published, Icons.Filled.CalendarMonth)
    VerticalDivider(
        modifier = Modifier.padding(6.dp),
        thickness = 2.dp,
        color = Color.LightGray
    )
    BookInfo("Pages", "${book.pages}", Icons.AutoMirrored.Filled.MenuBook)
    VerticalDivider(
        modifier = Modifier.padding(6.dp),
        thickness = 2.dp,
        color = Color.LightGray
    )
    BookInfo("Reviews", "${book.reviews}", Icons.Filled.Star)

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