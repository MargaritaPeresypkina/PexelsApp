package com.example.pexelsapp.presentation.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.PexelsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val repository: PexelsRepository
) : ViewModel() {

    private val _bookmarks = MutableStateFlow<List<Photo>>(emptyList())
    val bookmarks: StateFlow<List<Photo>> = _bookmarks

    fun loadBookmarks() {
        viewModelScope.launch {
            _bookmarks.value = repository.getBookmarks()
        }
    }
}
