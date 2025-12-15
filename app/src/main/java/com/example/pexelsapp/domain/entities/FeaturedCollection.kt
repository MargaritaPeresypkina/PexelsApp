package com.example.pexelsapp.domain.entities

data class FeaturedCollection(
    val id: String,
    val title: String,
    val isActive: Boolean = false
)