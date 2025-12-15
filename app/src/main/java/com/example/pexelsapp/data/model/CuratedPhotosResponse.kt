package com.example.pexelsapp.data.model

import kotlinx.serialization.SerialName

data class CuratedPhotosResponse(
    @SerialName("page") val page: Int,
    @SerialName("per_page") val perPage: Int,
    @SerialName("photos") val photos: List<PhotoDto>,
    @SerialName("total_results") val totalResults: Int,
    @SerialName("prev_page") val prevPage: Int?,
    @SerialName("next_page") val nextPage: Int?
)
