package com.bksapp.bookshare.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.data.local.entity.BookList
import com.bksapp.bookshare.ui.dashboard.homecomponent.SuccessContent
import com.bksapp.bookshare.utils.ErrorContent
import com.bksapp.bookshare.utils.LoadingContent


@Composable
fun HomeScreen(
    showBookDetail: (id: Int) -> Unit, callBookCat: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val homeUIState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    when {
        homeUIState.isLoading -> LoadingContent()
        homeUIState.error != null -> ErrorContent()
        else -> SuccessContent(
            homeUIState.bookCats,
            BookList(homeUIState.carouselData),
            BookList(homeUIState.allBooksData),
            BookList(homeUIState.bestSellerDataData),
            showBookDetail, callBookCat
        )
    }
}



