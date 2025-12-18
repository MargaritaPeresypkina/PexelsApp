package com.example.pexelsapp.presentation.bookmarks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pexelsapp.R
import com.example.pexelsapp.ui.theme.Black
import com.example.pexelsapp.ui.theme.Red

@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = hiltViewModel(),
    onPhotoClick: (Int) -> Unit,
    navController: NavController
) {
    val bookmarks by viewModel.bookmarks.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadBookmarks()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bookmarks",
            fontFamily = FontFamily(Font(R.font.mulish_latin_700_normal)),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Black
        )
        Spacer(modifier = Modifier.height(24.dp))
        if (bookmarks.isEmpty()) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "You haven't saved anything yet",
                    fontFamily = FontFamily(Font(R.font.mulish_latin_500_normal)),
                    fontSize = 14.sp,
                    color = Color(0xFF1E1E1E)
                )
                Text(
                    modifier = Modifier
                        .clickable{
                            navController.navigate("home")
                        },
                    text = "Explore",
                    fontFamily = FontFamily(Font(R.font.mulish_latin_700_normal)),
                    color = Red,
                    fontSize = 18.sp
                )
            }
        } else {
            MasonryGridWithName(
                photos = bookmarks,
                columns = 2,
                onPhotoClick = onPhotoClick,
                showPhotographerName = true
            )
        }
    }
}