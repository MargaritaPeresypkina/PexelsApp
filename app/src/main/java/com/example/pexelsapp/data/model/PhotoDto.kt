package com.example.pexelsapp.data.model

import kotlinx.serialization.SerialName

data class PhotoDto(
    @SerialName("id") val id: Int,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("url") val url: String,
    @SerialName("photographer") val photographer: String,
    @SerialName("photographer_url") val photographerUrl: String,
    @SerialName("avg_color") val avgColor: String,
    @SerialName("alt") val alt: String,
    @SerialName("src") val src: Src
) {
    data class Src(
        @SerialName("original") val original: String,
        @SerialName("large2x") val large2x: String,
        @SerialName("large") val large: String,
        @SerialName("medium") val medium: String,
        @SerialName("small") val small: String,
        @SerialName("portrait") val portrait: String,
        @SerialName("landscape") val landscape: String,
        @SerialName("tiny") val tiny: String
    )
}
