package worldimage.watchme.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import worldimage.watchme.R
import worldimage.watchme.data.remote.MovieApi
import worldimage.watchme.domain.model.MovieDetails
import worldimage.watchme.ui.theme.MovieBannerTextBackground
import worldimage.watchme.utils.toHourAndMinute
import worldimage.watchme.utils.toYear

@Composable
fun MovieDetailsImageOverlayText(
    movieDetails: MovieDetails
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MovieBannerTextBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            // Background poster
            val imageUrl = MovieApi.IMAGE_URL + movieDetails.backdrop_path
            AsyncImage(
                model = imageUrl,
                contentDescription = movieDetails.title + " image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                contentScale = ContentScale.FillHeight,
                placeholder = painterResource(R.drawable.placeholder_imageloading),
                error = painterResource(R.drawable.placeholder_imagenotfound)
            )

            // Fade to background (black in this case)
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, // top
                                MovieBannerTextBackground // bottom
                            ),
                            startY = 200f, // where fade starts
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            // Overlay UI content at bottom
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) {
                        Row {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = movieDetails.title,
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            ReviewComponents(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                voteAverage = movieDetails.vote_average,
                                voteCount = movieDetails.vote_count
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = movieDetails.release_date.toYear(),
                                color = Color.White.copy(alpha = 0.5f),
                                style = MaterialTheme.typography.labelMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Box(
                                modifier = Modifier
                                    .height(4.dp)
                                    .width(4.dp)
                                    .clip(RoundedCornerShape(32.dp))
                                    .background(Color.White.copy(alpha = 0.5f))
                            )
                            LazyRow(
                                modifier = Modifier
                                    .wrapContentWidth(),
                                horizontalArrangement = Arrangement.spacedBy(1.dp),
                            ) {
                                itemsIndexed(movieDetails.genres.take(3)) { index, item ->
                                    Text(
                                        text = if (index < movieDetails.genres.lastIndex) "${item.name}, " else item.name,
                                        color = Color.White.copy(alpha = 0.5f),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .height(4.dp)
                                    .width(4.dp)
                                    .clip(RoundedCornerShape(32.dp))
                                    .background(Color.White.copy(alpha = 0.5f))
                            )
                            Text(
                                text = movieDetails.runtime.toHourAndMinute(),
                                color = Color.White.copy(alpha = 0.5f),
                                style = MaterialTheme.typography.labelMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMovieDetailsImageOverlayText() {
    val movieDetails = MovieDetails(
        id = 0,
        title = "",
        poster_path = "",
        genres = emptyList(),
        vote_average = 0.0,
        vote_count = 0,
        release_date = "2025-07-29",
        overview = "",
        runtime = 93,
        backdrop_path = ""
    )
    MovieDetailsImageOverlayText(movieDetails)
}