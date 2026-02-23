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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepo : BookRepository
): ViewModel() {

    private val _bookState = MutableStateFlow<NetworkStatus<List<Book>>>(NetworkStatus.Idle)
    val bookState = _bookState.asStateFlow()

    init{
        _bookState.value = NetworkStatus.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO)
             {
                _bookState.value = NetworkStatus.Success(bookRepo.getBooks())
             }
         }
        }


}