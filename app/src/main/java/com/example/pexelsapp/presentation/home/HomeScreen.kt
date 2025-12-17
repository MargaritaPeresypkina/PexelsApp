package com.example.pexelsapp.presentation.home

import MasonryGrid
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.pexelsapp.R
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pexelsapp.ui.theme.Black
import com.example.pexelsapp.ui.theme.DarkGrey
import com.example.pexelsapp.ui.theme.Grey
import com.example.pexelsapp.ui.theme.Red
import com.example.pexelsapp.ui.theme.White


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onPhotoClick: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val featuredCollections by viewModel.featuredCollections.collectAsState()
    val photos by viewModel.curatedPhotos.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp, bottom = 12.dp)
    ) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.onSearchQueryChanged(it.text)
            },
            placeholder = {
                Text(
                    text = "Search",
                    fontFamily = FontFamily(Font(R.font.mulish_latin_400_normal)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.02.sp
                )
            },
            modifier = Modifier.height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Grey,
                unfocusedContainerColor = Grey,
                cursorColor = Black,
                focusedTextColor = Black,
                unfocusedTextColor = Black,
                focusedPlaceholderColor = DarkGrey,
                unfocusedPlaceholderColor = DarkGrey
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search",
                    tint = Red,
                    modifier = Modifier.padding(top = 17.dp, bottom = 17.dp, start = 20.dp, end = 12.dp)
                )
            },
            trailingIcon = {
                AnimatedVisibility(visible = searchQuery.text.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = "Clear",
                        modifier = Modifier
                            .padding(end = 20.dp, top = 18.dp, bottom = 18.dp)
                            .clickable {
                                searchQuery = TextFieldValue("")
                                viewModel.clearSearch()
                            },
                        tint = DarkGrey
                    )
                }
            },
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.mulish_latin_400_normal)),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.02.sp
            )
        )
        if (featuredCollections.isEmpty() && isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = Red,
                trackColor = Grey
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(11.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = featuredCollections) { collection ->
                val backgroundColor = if (collection.isActive) Red else Grey
                val textColor = if (collection.isActive) White else Black

                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(backgroundColor)
                        .clickable {
                            searchQuery = TextFieldValue(collection.title)
                            viewModel.onCollectionClicked(collection)
                        }
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = collection.title,
                        color = textColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.mulish_latin_400_normal)),
                        letterSpacing = 0.02.sp,
                        lineHeight = 14.sp
                    )
                }
            }
        }
        if (featuredCollections.isNotEmpty() && isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                color = Red,
                trackColor = Grey
            )
        }



        Spacer(modifier = Modifier.height(24.dp))

        AnimatedContent(
            targetState = photos,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) togetherWith
                        fadeOut(animationSpec = tween(200))
            },
            label = "Photos animation"
        ) { animatedPhotos ->
            MasonryGrid(
                photos = animatedPhotos,
                columns = 2,
                onPhotoClick = onPhotoClick
            )
        }
    }
}