package com.bksapp.bookshare.utils

import androidx.compose.runtime.compositionLocalOf
import com.bksapp.bookshare.data.local.entity.Book

val localBook = compositionLocalOf<Book>{
    error("No Book provided")
}