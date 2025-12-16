package com.example.pexelsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pexelsapp.data.db.dao.FeaturedCollectionDao
import com.example.pexelsapp.data.db.dao.PhotoDao
import com.example.pexelsapp.data.db.entities.FeaturedCollectionEntity
import com.example.pexelsapp.data.db.entities.PhotoEntity



@Database(
    entities = [PhotoEntity::class, FeaturedCollectionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    abstract fun featuredCollectionDao(): FeaturedCollectionDao
}