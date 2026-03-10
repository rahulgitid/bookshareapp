package com.bksapp.bookshare.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest.*
import coil3.request.crossfade
import com.bksapp.bookshare.R

@Composable
fun ImageLoader(url : String,isCustom:Boolean = false,quality: FilterQuality){

    if(!isCustom) {
        AsyncImage(
            model = url,
            contentDescription = "book",
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background),
            filterQuality = quality,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
    }
    else{
        SubcomposeAsyncImage(
            model = url,
            contentDescription = "book",
            loading = { CircularProgressIndicator(modifier = Modifier.size(4.dp).align(Alignment.Center)) },
            error = { painterResource(R.drawable.ic_launcher_background)},
            filterQuality = quality,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}