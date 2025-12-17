package com.example.pexelsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.entities.FeaturedCollection
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.PexelsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PexelsRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val _featuredCollections = MutableStateFlow<List<FeaturedCollection>>(emptyList())
    val featuredCollections: StateFlow<List<FeaturedCollection>> = _featuredCollections

    private val _curatedPhotos = MutableStateFlow<List<Photo>>(emptyList())
    val curatedPhotos: StateFlow<List<Photo>> = _curatedPhotos

    init {
        viewModelScope.launch {
            withLoading {
                val collections = repository.getFeaturedCollections(1, 7)
                _featuredCollections.value = collections
                loadCuratedPhotos()
            }
        }
    }

    fun loadCuratedPhotos() {
        viewModelScope.launch {
            withLoading {
                _curatedPhotos.value = repository.getCuratedPhotos(1, 30)
            }
        }
    }

    private fun updateActiveByQuery(query: String) {
        _featuredCollections.value = _featuredCollections.value.map {
            it.copy(isActive = it.title.equals(query, ignoreCase = true))
        }
    }


    fun searchPhotosByQuery(query: String) {
        viewModelScope.launch {
            withLoading {
                _curatedPhotos.value = repository.searchPhotos(query, 1, 30)
                updateActiveByQuery(query)
            }
        }
    }

    fun onCollectionClicked(collection: FeaturedCollection) {
        _featuredCollections.value = _featuredCollections.value.map {
            it.copy(isActive = it.id == collection.id)
        }
        searchPhotosByQuery(collection.title)
    }

    fun onSearchQueryChanged(query: String) {
        searchPhotosByQuery(query)
        _featuredCollections.value = _featuredCollections.value.map {
            it.copy(isActive = it.title.equals(query, ignoreCase = true))
        }
    }

    fun clearSearch() {
        viewModelScope.launch {
            _curatedPhotos.value = repository.getCuratedPhotos(1, 30)

            _featuredCollections.value = _featuredCollections.value.map {
                it.copy(isActive = false)
            }
        }
    }

    private suspend fun <T> withLoading(block: suspend () -> T): T {
        _isLoading.value = true
        return try {
            block()
        } finally {
            _isLoading.value = false
        }
    }
}
