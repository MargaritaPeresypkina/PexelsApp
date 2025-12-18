package com.example.pexelsapp.presentation.bookmarks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.domain.entities.Photo

@Composable
fun MasonryGridWithName(
    photos: List<Photo>,
    columns: Int = 2,
    onPhotoClick: (Int) -> Unit,
    showPhotographerName: Boolean = false
) {
    val columnLists = remember(photos) {
        List(columns) { mutableListOf<Photo>() }.apply {
            photos.forEachIndexed { index, photo ->
                this[index % columns].add(photo)
            }
        }
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                columnLists.forEach { columnPhotos ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        columnPhotos.forEach { photo ->
                            PhotoItemWithName(
                                photo = photo,
                                onClick = { onPhotoClick(photo.id) },
                                showPhotographerName = showPhotographerName
                            )
                        }
                    }
                }
            }
        }
    }
}