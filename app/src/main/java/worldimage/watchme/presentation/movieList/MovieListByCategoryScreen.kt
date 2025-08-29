package worldimage.watchme.presentation.movieList

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import worldimage.watchme.domain.model.MovieList
import worldimage.watchme.navigation.Screen
import worldimage.watchme.presentation.MovieViewModel
import worldimage.watchme.presentation.components.MovieBanner

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MovieListByCategoryScreen(
    modifier: Modifier = Modifier,
    movieList: List<MovieList>,
    isShowTitle: Boolean = false,
    title: String = "",
    navController: NavController
) {
    val viewModel: MovieViewModel = hiltViewModel()

    Column {
        if (isShowTitle) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable(true, onClick = {
                            println("On Click called!")
                        }),
                    text = "See all",
                    color = Color.White.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(movieList) { item ->
                MovieBanner(
                    movieDetails = item,
                ) {
                    navController.navigate(route = Screen.MovieDetails.route + "?movieId=${item.id}")
                }
            }
        }
    }

}
//
//@Preview()
//@Composable
//private fun PrevMovieBannerList() {
//    val movieList = listOf(
//        MovieDetails(
//            backdrop_path = "/eU7IfdWq8KQy0oNd4kKXS0QUR08.jpg",
//            id = 1061474,
//            original_language = "en",
//            original_title = "Superman",
//            overview = "Superman, a journalist in Metropolis, embarks on a journey to reconcile his Kryptonian heritage with his human upbringing as Clark Kent.",
//            popularity = 568.5633,
//            poster_path = "/ombsmhYUqR4qqOLOxAyr5V8hbyv.jpg",
//            release_date = "2025-07-09",
//            title = "Superman",
//            vote_average = 7.555,
//            vote_count = 2648
//        ),
//        MovieDetails(
//            backdrop_path = "/eU7IfdWq8KQy0oNd4kKXS0QUR08.jpg",
//            id = 1061474,
//            original_language = "en",
//            original_title = "Superman",
//            overview = "Superman, a journalist in Metropolis, embarks on a journey to reconcile his Kryptonian heritage with his human upbringing as Clark Kent.",
//            popularity = 568.5633,
//            poster_path = "/ombsmhYUqR4qqOLOxAyr5V8hbyv.jpg",
//            release_date = "2025-07-09",
//            title = "Superman",
//            vote_average = 7.555,
//            vote_count = 2648
//        ),
//        MovieDetails(
//            backdrop_path = "/eU7IfdWq8KQy0oNd4kKXS0QUR08.jpg",
//            id = 1061474,
//            original_language = "en",
//            original_title = "Superman",
//            overview = "Superman, a journalist in Metropolis, embarks on a journey to reconcile his Kryptonian heritage with his human upbringing as Clark Kent.",
//            popularity = 568.5633,
//            poster_path = "/ombsmhYUqR4qqOLOxAyr5V8hbyv.jpg",
//            release_date = "2025-07-09",
//            title = "Superman",
//            vote_average = 7.555,
//            vote_count = 2648
//        )
//    )
//    MovieListByCategoryScreen(movieList = movieList, isShowTitle = true, title = "Top Rated Movies")
//}