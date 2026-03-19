package com.bksapp.bookshare.ui.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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



