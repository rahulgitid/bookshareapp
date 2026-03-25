package com.bksapp.bookshare.utils

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.bottomShadow(
    color: Color = Color.LightGray,
    alpha: Float = 0.5f,
    blurRadius: Dp = 6.dp,
    offsetY: Dp = 4.dp,
) = this.drawBehind {
    val paint = Paint()
    val frameworkPaint = paint.asFrameworkPaint()
    frameworkPaint.color = color.copy(alpha = alpha).toArgb()

    // Draw the shadow with an offset
    drawIntoCanvas { canvas ->
        canvas.save()
        // Clip the top part of the shadow so it only appears at the bottom
        canvas.clipRect(
            left = -size.width,
            top = 0f,
            right = size.width * 2,
            bottom = size.height + blurRadius.toPx()
        )
        frameworkPaint.setShadowLayer(
            blurRadius.toPx(),
            0f, // No horizontal offset
            offsetY.toPx(), // Vertical offset
            frameworkPaint.color
        )
        canvas.drawRect(0f, 0f, size.width, size.height, paint)
        canvas.restore()
    }
}