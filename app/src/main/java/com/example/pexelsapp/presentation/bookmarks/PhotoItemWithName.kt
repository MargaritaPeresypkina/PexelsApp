package com.example.pexelsapp.presentation.bookmarks


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.pexelsapp.R
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.ui.theme.Grey
import com.example.pexelsapp.ui.theme.White

@Composable
fun PhotoItemWithName(
    photo: Photo,
    onClick: () -> Unit,
    showPhotographerName: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(photo.width.toFloat() / photo.height.toFloat())
            .clip(RoundedCornerShape(20.dp))
            .background(Grey)
            .clickable(onClick = onClick)
    ) {
        SubcomposeAsyncImage(
            model = photo.imageUrl,
            contentDescription = photo.description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        ) {
            val state = painter.state
            val imageAlpha by animateFloatAsState(
                targetValue = if (state is coil.compose.AsyncImagePainter.State.Success) 1f else 0f,
                animationSpec = tween(durationMillis = 700),
                label = "imageAlpha"
            )
            val placeholderAlpha by animateFloatAsState(
                targetValue = if (state is coil.compose.AsyncImagePainter.State.Success) 0f else 1f,
                animationSpec = tween(durationMillis = 250),
                label = "placeholderAlpha"
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Grey)
                    .alpha(placeholderAlpha),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.image_placeholder),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    alpha = 0.3f
                )
            }
            SubcomposeAsyncImageContent(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(imageAlpha)
            )
        }

        if (showPhotographerName) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(33.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(alpha = 0.4f))
                    .padding( vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = photo.photographerName,
                    color = White,
                    fontFamily = FontFamily(Font(R.font.mulish_latin_400_normal)),
                    fontSize = 14.sp
                )
            }
        }
    }
}