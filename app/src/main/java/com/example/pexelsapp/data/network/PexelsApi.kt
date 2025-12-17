package com.example.pexelsapp.data.network

import com.example.pexelsapp.data.model.CuratedPhotosResponse
import com.example.pexelsapp.data.model.FeaturedCollectionsResponse
import com.example.pexelsapp.data.model.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsApi {

    @GET("v1/curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): CuratedPhotosResponse

    @GET("v1/collections/featured")
    suspend fun getFeaturedCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): FeaturedCollectionsResponse

    @GET("v1/photos/{id}")
    suspend fun getPhotoDetails(
        @Path("id") photoId: Int
    ): PhotoDto

    @GET("v1/search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): CuratedPhotosResponse
}
