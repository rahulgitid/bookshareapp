package com.bksapp.bookshare.ui.dashboard.homecomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Forward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookSectionHeader(title: String){

    Row(modifier= Modifier
        .fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically){
        Text(modifier =
            Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.titleLarge
            )

        IconButton(onClick = {}) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "")

        }
    }
}