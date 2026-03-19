package com.bksapp.bookshare.ui.dashboard

import androidx.compose.runtime.Immutable
import com.bksapp.bookshare.data.local.entity.Book


@Immutable
data class HomeUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val bookCats :List<String> = emptyList(),
    val allBooksData :List<Book> = emptyList(),
    val carouselData :List<Book> = emptyList(),
    val bestSellerDataData :List<Book> = emptyList())