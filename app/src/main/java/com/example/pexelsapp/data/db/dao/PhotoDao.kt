package com.example.pexelsapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pexelsapp.data.db.entities.PhotoEntity

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    suspend fun getAllPhotos(): List<PhotoEntity>

    @Query("SELECT * FROM photos WHERE id = :photoId LIMIT 1")
    suspend fun getPhotoById(photoId: Int): PhotoEntity?

    @Query("SELECT * FROM photos WHERE isBookmarked = 1")
    suspend fun getIsBookmarkedPhotos(): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: PhotoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhotos(photos: List<PhotoEntity>)

    @Query("DELETE FROM photos WHERE isBookmarked = 0")
    suspend fun cleanCache()

    @Query("SELECT EXISTS(SELECT 1 FROM photos WHERE id = :photoId AND isBookmarked = 1)")
    suspend fun isBookmarked(photoId: Int): Boolean

    @Delete
    suspend fun deletePhoto(photo: PhotoEntity)
}