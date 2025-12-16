package com.example.pexelsapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pexelsapp.data.db.entities.FeaturedCollectionEntity

@Dao
interface FeaturedCollectionDao {

    @Query("SELECT * FROM featured_collections")
    suspend fun getAllFeaturedCollections(): List<FeaturedCollectionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFeaturedCollections(collections: List<FeaturedCollectionEntity>)

    @Query("DELETE FROM featured_collections")
    suspend fun clearAll()

    @Query("UPDATE featured_collections SET isActive = 0")
    suspend fun clearActiveFeaturedCollection()

    @Query("UPDATE featured_collections SET isActive = 1 WHERE id = :collectionId")
    suspend fun setActiveFeaturedCollection(collectionId: Int)
}