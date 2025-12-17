import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.pexelsapp.R
import com.example.pexelsapp.domain.entities.Photo
import com.example.pexelsapp.ui.theme.Grey

@Composable
fun PhotoItem(
    photo: Photo,
    onClick: () -> Unit
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
            modifier = Modifier.fillMaxSize()
        ) {

            val state = painter.state

            val imageAlpha by androidx.compose.animation.core.animateFloatAsState(
                targetValue = if (state is coil.compose.AsyncImagePainter.State.Success) 1f else 0f,
                animationSpec = androidx.compose.animation.core.tween(
                    durationMillis = 700
                ),
                label = "imageAlpha"
            )

            val placeholderAlpha by androidx.compose.animation.core.animateFloatAsState(
                targetValue = if (state is coil.compose.AsyncImagePainter.State.Success) 0f else 1f,
                animationSpec = androidx.compose.animation.core.tween(
                    durationMillis = 250
                ),
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
    }
}
