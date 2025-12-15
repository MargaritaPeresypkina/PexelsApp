package com.example.pexelsapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "featured_collections")
data class FeaturedCollectionEntity(
    @PrimaryKey val id: String,
    val title: String,
    val isActive: Boolean = false,
    val cacheTime: Int? = null
)
