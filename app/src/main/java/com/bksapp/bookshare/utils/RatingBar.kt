package com.bksapp.bookshare.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.ui.theme.Primary

@Composable
fun RatingBar(rating: Double){
    Row {
        (1..5).forEach {i->
            val icon = when {
                rating >= i -> Icons.Filled.StarRate
                rating >= i - 0.5f -> Icons.AutoMirrored.Filled.StarHalf // Requires androidx.compose.material:material-icons-extended
                else -> Icons.Outlined.StarRate
            }
            Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = icon,
                    tint = Primary,
                    contentDescription = ""
                )
        }
    }

}