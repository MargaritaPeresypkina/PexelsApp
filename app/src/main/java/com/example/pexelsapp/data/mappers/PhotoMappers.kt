package com.example.pexelsapp.data.mappers

import com.example.pexelsapp.data.db.entities.PhotoEntity
import com.example.pexelsapp.data.model.PhotoDto
import com.example.pexelsapp.domain.entities.Photo

fun PhotoDto.toDomain() = Photo(
    id = id,
    width = width,
    height = height,
    imageUrl = src.medium,
    photographerName = photographer,
    avgColor = avgColor,
    description = alt,
    isBookmarked = false
)

fun Photo.toEntity() = PhotoEntity(
    id = id,
    width = width,
    height = height,
    imageUrl = imageUrl,
    photographerName = photographerName,
    avgColor = avgColor,
    description = description,
    isBookmarked = isBookmarked
)

fun PhotoEntity.toDomain() = Photo(
    id = id,
    width = width,
    height = height,
    imageUrl = imageUrl,
    photographerName = photographerName,
    avgColor = avgColor,
    description = description,
    isBookmarked = isBookmarked
)