package com.example.pexelsapp.data.mappers

import com.example.pexelsapp.data.db.dao.FeaturedCollectionDao
import com.example.pexelsapp.data.db.entities.FeaturedCollectionEntity
import com.example.pexelsapp.data.model.FeaturedCollectionDto
import com.example.pexelsapp.domain.entities.FeaturedCollection

fun FeaturedCollectionDto.toDomain() = FeaturedCollection(
    id = id,
    title = title,
    isActive = false
)

fun FeaturedCollection.toEntity() = FeaturedCollectionEntity(
    id = id,
    title = title,
    isActive = isActive
)

fun FeaturedCollectionEntity.toDomain() = FeaturedCollection(
    id = id,
    title = title,
    isActive = isActive
)