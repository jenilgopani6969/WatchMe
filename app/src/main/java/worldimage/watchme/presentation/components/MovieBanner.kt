package worldimage.watchme.presentation.components

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import worldimage.watchme.R
import worldimage.watchme.data.remote.MovieApi
import worldimage.watchme.domain.model.MovieDetails
import worldimage.watchme.ui.theme.CategoryBackground
import worldimage.watchme.utils.toYear

@Composable
fun MovieBanner(
    movieDetails: MovieDetails,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(164.dp)
            ) {
                val imageUrl = MovieApi.IMAGE_URL + movieDetails.poster_path
                AsyncImage(
                    model = imageUrl,
                    contentDescription = movieDetails.title + " image",
                    modifier = Modifier.wrapContentWidth().wrapContentHeight(),
                )
                Box(
                    modifier = Modifier
                        .background(CategoryBackground)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = movieDetails.title,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = movieDetails.release_date.toYear(),
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

            }

    }
}

@Preview(showBackground = true)
@Composable
private fun PrevMovieBanner() {
    MovieBanner(
        movieDetails = MovieDetails(
            backdrop_path = "/eU7IfdWq8KQy0oNd4kKXS0QUR08.jpg",
            id = 1061474,
            original_language = "en",
            original_title = "Superman",
            overview = "Superman, a journalist in Metropolis, embarks on a journey to reconcile his Kryptonian heritage with his human upbringing as Clark Kent.",
            popularity = 568.5633,
            poster_path = "/ombsmhYUqR4qqOLOxAyr5V8hbyv.jpg",
            release_date = "2025-07-09",
            title = "Superman",
            vote_average = 7.555,
            vote_count = 2648
        )
    ) {}
}
