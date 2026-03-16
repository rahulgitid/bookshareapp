package com.bksapp.bookshare.data.local.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val price: Int,
    val originalPrice: Int,
    val rating: Double,
    val reviews: Int,
    val cover: String,
    val category: String,
    val publisher: String,
    val pages: Int,
    val published: String,
    val language: String
)
