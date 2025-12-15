package com.example.pexelsapp.data.model

import kotlinx.serialization.SerialName

data class FeaturedCollectionsResponse(
    @SerialName("collections") val collections: List<FeaturedCollectionDto>,
    @SerialName("page") val page: Int,
    @SerialName("per_page") val perPage: Int,
    @SerialName("total_results") val totalResults: Int,
    @SerialName("prev_page") val prevPage: Int?,
    @SerialName("next_page") val nextPage: Int?
)
