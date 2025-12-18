package com.example.pexelsapp.data.repository

import com.example.pexelsapp.data.db.AppDatabase
import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.data.mappers.toEntity
import com.example.pexelsapp.data.network.PexelsApi
import com.example.pexelsapp.domain.entities.FeaturedCollection
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.domain.repository.PexelsRepository
import javax.inject.Inject

class PexelsRepositoryImpl @Inject constructor(
    val api: PexelsApi,
    val db: AppDatabase
): PexelsRepository  {
    private val photoDao = db.photoDao()
    private val featuredCollectionDao = db.featuredCollectionDao()

    override suspend fun getCuratedPhotos(page: Int, perPage: Int): List<Photo> {
        return try {
            val response = api.getCuratedPhotos(page, perPage)
            val photos = response.photos.map { it.toDomain() }
            photoDao.insertAllPhotos(photos.map {
                it.toEntity()
            } )
            photos
        } catch(e: Exception) {
            photoDao.getAllPhotos().map{ it.toDomain() }
        }

    }

    override suspend fun getFeaturedCollections(page: Int, perPage: Int): List<FeaturedCollection> {
        return try{
            val response = api.getFeaturedCollections(page, perPage)
            val collections = response.collections.map { it.toDomain() }
            featuredCollectionDao.insertAllFeaturedCollections(collections.map{
                it.toEntity()
            })
            collections
        } catch(e: Exception) {
            featuredCollectionDao.getAllFeaturedCollections().map{ it.toDomain() }
        }
    }

    override suspend fun getPhotoDetails(photoId: Int): Photo? {
        return try {
            val photo = api.getPhotoDetails(photoId).toDomain()
            photoDao.insertPhoto(photo.toEntity())
            photo
        } catch (e: Exception) {
            photoDao.getPhotoById(photoId)?.toDomain()
        }
    }

    override suspend fun getBookmarks(): List<Photo> = photoDao.getIsBookmarkedPhotos().map{it.toDomain()}

    override suspend fun addBookmark(photo: Photo) = photoDao.insertPhoto(photo.toEntity().copy(isBookmarked = true))

    override suspend fun removeBookmark(photo: Photo) = photoDao.updateBookmarked(photo.id, false)

    override suspend fun searchPhotos(query: String, page: Int, perPage: Int): List<Photo> {
        return try {
            val response = api.searchPhotos(query, page, perPage)
            val photos = response.photos.map { it.toDomain() }
            photoDao.insertAllPhotos(photos.map { it.toEntity() })
            photos
        } catch(e: Exception) {
            photoDao.getAllPhotos().map { it.toDomain() }
        }
    }

}