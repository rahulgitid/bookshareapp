package com.bksapp.bookshare.data.local.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Book(
    val id : Int,
    val title : String,
    val price : Int,
    val cover : String
)
