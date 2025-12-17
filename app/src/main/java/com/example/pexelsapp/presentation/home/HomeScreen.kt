package com.example.pexelsapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onPhotoClick: (Int) -> Unit
) {
    val photo = 1

    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Home")
        Button(
            onClick = { onPhotoClick(photo) },
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text(text = "Photo $photo")
        }
    }
}

