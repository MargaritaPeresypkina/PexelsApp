package com.example.pexelsapp.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pexelsapp.R
import coil.compose.AsyncImage
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.ui.theme.Black
import com.example.pexelsapp.ui.theme.Grey
import com.example.pexelsapp.ui.theme.Red


@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBack: () -> Unit,
    onDownload: (Photo) -> Unit
) {
    val photo by viewModel.photo.collectAsState()

    photo?.let { p ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp,end = 24.dp, top  = 17.dp, bottom = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { onBack() }.clip(RoundedCornerShape(12.dp))
                            .background(Grey),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = "Back"
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = p.photographerName,
                        fontFamily = FontFamily(Font(R.font.mulish_latin_700_normal)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Spacer(modifier = Modifier.size(40.dp))
                }

                Spacer(modifier = Modifier.height(29.dp))

                AsyncImage(
                    model = p.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .width(180.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Grey)
                        .clickable { onDownload(p) }
                        .padding(horizontal = 6.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Red),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_download),
                                contentDescription = "Download"
                            )
                        }

                        Spacer(modifier = Modifier.width(17.dp))

                        Text(
                            text = "Download",
                            color = Black,
                            fontFamily = FontFamily(Font(R.font.mulish_latin_600_normal)),
                            fontSize = 14.sp
                        )
                    }
                }

                Image(
                    painter = painterResource(
                        if (p.isBookmarked)
                            R.drawable.ic_bookmark_active
                        else
                            R.drawable.ic_bookmark_inactive
                    ),
                    contentDescription = "Bookmark",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { viewModel.toggleBookmark() }
                )
            }
        }
    }
}
