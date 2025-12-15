package com.example.pexelsapp.domain.entities

data class Photo (
    val id: Int,
    val width: Int,
    val height: Int,
    val imageUrl: String,
    val photographerName: String,
    val avgColor: String?,
    val description: String?,
    val isBookmarked: Boolean = false
)