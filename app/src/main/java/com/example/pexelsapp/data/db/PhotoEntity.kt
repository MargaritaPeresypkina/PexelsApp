package com.example.pexelsapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: Int,
    val width: Int,
    val height: Int,
    val imageUrl: String,
    val photographerName: String,
    val avgColor: String?,
    val description: String?,
    val isBookmarked: Boolean = false,
    val cacheTime: Int? = null
)
