package worldimage.watchme.presentation.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import worldimage.watchme.R
import worldimage.watchme.data.remote.MovieApi
import worldimage.watchme.presentation.MovieViewModel
import worldimage.watchme.presentation.components.MovieDetailsCategoryItem
import worldimage.watchme.presentation.components.ReviewComponents
import worldimage.watchme.presentation.movieList.MovieListByCategoryScreen
import worldimage.watchme.ui.theme.CategoryBackground

@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieId: String?
) {
    val movieViewModel = hiltViewModel<MovieViewModel>()
    LaunchedEffect(Unit) {
        movieId?.let { movieViewModel.getMovieDetails(it) }
        movieId?.let { movieViewModel.getRecommendedMovie(it) }
    }

    val movieDetailsState = movieViewModel.movieDetailsState.collectAsState().value
    val recommendedMovieState = movieViewModel.movieRecommendedMovieState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val imageUrl = MovieApi.IMAGE_URL + movieDetailsState.movieDetails.poster_path
        AsyncImage(
            model = imageUrl,
            contentDescription = movieDetailsState.movieDetails.title + " image",
            modifier = Modifier
                .fillMaxWidth()
                .height(444.dp),
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(R.drawable.placeholder_imageloading),
            error = painterResource(R.drawable.placeholder_imagenotfound)
        )
        Box(
            modifier = Modifier
                .background(CategoryBackground)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)

                        ) {
                            Text(
                                text = movieDetailsState.movieDetails.title,
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            LazyRow(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .wrapContentWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(movieDetailsState.movieDetails.genres) { item ->
                                    MovieDetailsCategoryItem(
                                        genre = item,
                                    )
                                }
                            }
                        }
                        ReviewComponents(
                            modifier = Modifier,
                            voteAverage = movieDetailsState.movieDetails.vote_average,
                            voteCount = movieDetailsState.movieDetails.vote_count
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.1f))
                )
                if (recommendedMovieState.movieList.isNotEmpty()) {
                    MovieListByCategoryScreen(
                        movieList = recommendedMovieState.movieList,
                        isShowTitle = true,
                        title = stringResource(R.string.recommended_movies),
                        isSellAllVisible = false,
                        navController = navController,
                        isPopBackStack = true
                    )
                }
            }
        }
    }
}
