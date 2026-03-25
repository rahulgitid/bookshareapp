package com.bksapp.bookshare.utils

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.domain.cartItems

@Composable
fun BookTopBar(title: String,
               backButton: Boolean = false,
               isSearch:Boolean = false,
               isCart:Boolean = false,
               backAction:()->Unit = {},
               cartClick:()->Unit = {},
               cartCount:()->Int = {0}) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .bottomShadow(

                    )
                    .background(Color.White) // The background must be applied after the shadow modifier
                    .padding(16.dp)
            ) {
                if (backButton) IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = backAction
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )

                Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                    if (isSearch) IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Back")
                    }

                    if (isCart) BadgedBox(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(32.dp),
                        badge = {
                            if (cartCount() > 0) {
                                Badge {
                                    Text(text = "${cartCount()}")
                                }
                            }

                        }) {
                        IconButton(onClick = cartClick) {
                            Icon(
                                imageVector = Icons.Outlined.ShoppingCart,
                                contentDescription = "Back"
                            )
                        }
                    }


                }
            }



}