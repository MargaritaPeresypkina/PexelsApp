package com.example.pexelsapp.data.model

import kotlinx.serialization.SerialName

data class FeaturedCollectionDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("private") val private: Boolean,
    @SerialName("media_count") val mediaCount: Int,
    @SerialName("photos_count") val photosCount: Int,
    @SerialName("videos_count") val videosCount: Int
)