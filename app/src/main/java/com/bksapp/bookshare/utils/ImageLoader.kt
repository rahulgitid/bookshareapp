package com.bksapp.bookshare.utils

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest.*
import coil3.request.crossfade
import com.bksapp.bookshare.R

@Composable
fun ImageLoader(url : String,isCustom:Boolean = false){

    val context = LocalContext.current
    if(!isCustom) {
        AsyncImage(
            model = Builder(context)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = "book",
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background),
            contentScale = ContentScale.FillWidth,
        )
    }
    else{
        SubcomposeAsyncImage(
            model = Builder(context)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = "book",
            loading = { CircularProgressIndicator() },
            error = { painterResource(R.drawable.ic_launcher_background)},
            contentScale = ContentScale.FillWidth
        )
    }
}