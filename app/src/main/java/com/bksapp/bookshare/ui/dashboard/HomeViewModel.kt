package com.bksapp.bookshare.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bksapp.bookshare.data.local.entity.Book
import com.bksapp.bookshare.data.repository.NetworkStatus
import com.bksapp.bookshare.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepo : BookRepository
): ViewModel() {
   private val _bookState = MutableStateFlow<NetworkStatus<List<Book>>>(NetworkStatus.Idle)
    val bookState = _bookState.asStateFlow()

    private val _homeUIState = MutableStateFlow(HomeUIState())
    val homeUiState = _homeUIState.asStateFlow()


    init{

        viewModelScope.launch {
            _homeUIState.update{it.copy(isLoading = true)}
            withContext(Dispatchers.IO)
            {
                 val books = bookRepo.getBooks()
                 _homeUIState.update{
                     it.copy(
                         isLoading = false,
                      error = null,
                      bookCats  = books.map { book->book.category }.distinct(),
                      allBooksData  = books,
                      carouselData = books.subList(0,7),
                      bestSellerDataData  = books.subList(5,10)
                     )
                 }
             }
         }
        }


}