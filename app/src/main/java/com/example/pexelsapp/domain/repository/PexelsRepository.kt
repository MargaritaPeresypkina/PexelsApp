package com.example.pexelsapp.domain.repository

import com.example.pexelsapp.domain.entities.FeaturedCollection
import com.example.pexelsapp.domain.entities.Photo

interface PexelsRepository {
    suspend fun getCuratedPhotos(page: Int, perPage: Int): List<Photo>
    suspend fun getFeaturedCollections(page: Int, perPage: Int): List<FeaturedCollection>
    suspend fun getPhotoDetails(photoId: Int): Photo?
    suspend fun getBookmarks(): List<Photo>
    suspend fun addBookmark(photo: Photo)
    suspend fun removeBookmark(photo: Photo)
}