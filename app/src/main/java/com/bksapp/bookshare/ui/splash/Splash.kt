package com.bksapp.bookshare.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.R
import com.bksapp.bookshare.ui.theme.Primary
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay


@Composable
fun SplashScreen(goToMain : ()->Unit){

    val progress = remember { Animatable(1f,0.2f) }
    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
        )
        delay(200L)
        goToMain()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val topY = size.height * progress.value
                drawRect(
                    color = Primary,
                    topLeft = Offset(0f, topY),
                    size = Size(size.width, size.height-topY )
                )
            }
    ) {
        Image(modifier = Modifier
            .size(50.dp)
            .align(Alignment.Center),
            contentDescription = "",
            painter = painterResource(R.drawable.ic_launcher_background)
        )
    }

}