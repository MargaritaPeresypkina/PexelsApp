package com.example.pexelsapp.presentation.bookmarks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookmarksScreen(onPhotoClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        Text(text = "Bookmarks")
        Button(onClick = { onPhotoClick(1) }) {
            Text("Photo")
        }
    }
}