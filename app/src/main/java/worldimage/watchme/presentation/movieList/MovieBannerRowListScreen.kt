package worldimage.watchme.presentation.movieList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import worldimage.watchme.domain.model.Category
import worldimage.watchme.domain.model.MovieDetails
import worldimage.watchme.presentation.components.CategoryButton
import worldimage.watchme.presentation.components.MovieBanner

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MovieBannerRowListScreen(
    movieList: List<MovieDetails>,
) {
    //val viewModel: MovieViewModel = hiltViewModel()
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(movieList) { item ->
            MovieBanner(
                movieDetails = item,
            ) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevMovieBannerList() {
    val movieList = listOf(
        MovieDetails(
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
        ),
        MovieDetails(
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
        ),
        MovieDetails(
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
    )
    MovieBannerRowListScreen(movieList)
}