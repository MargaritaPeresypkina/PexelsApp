package com.example.pexelsapp.presentation.details

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
class DetailsViewModel @Inject constructor(
    private val repository: PexelsRepository
): ViewModel() {

    private val _photo = MutableStateFlow<Photo?>(null)
    val photo: StateFlow<Photo?> = _photo

    fun loadPhoto(id: Int) {
        viewModelScope.launch {
            _photo.value = repository.getPhotoDetails(id)
        }
    }

    fun toggleBookmark() {
        viewModelScope.launch {
            _photo.value?.let { current ->
                if (current.isBookmarked) {
                    repository.removeBookmark(current)
                    _photo.value = current.copy(isBookmarked = false)
                } else {
                    repository.addBookmark(current)
                    _photo.value = current.copy(isBookmarked = true)
                }
            }
        }
    }
}
