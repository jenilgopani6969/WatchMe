package worldimage.watchme.presentation.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import worldimage.watchme.R
import worldimage.watchme.domain.model.MovieDetails
import worldimage.watchme.presentation.MovieViewModel
import worldimage.watchme.presentation.components.CastItem
import worldimage.watchme.presentation.components.ExpandableStory
import worldimage.watchme.presentation.components.MovieDetailsImageOverlayText
import worldimage.watchme.presentation.movieList.MovieListByCategoryScreen
import worldimage.watchme.ui.theme.MovieBannerTextBackground

@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieId: String?
) {
    val movieViewModel = hiltViewModel<MovieViewModel>()
    LaunchedEffect(Unit) {
        movieId?.let { movieViewModel.getMovieDetails(it) }
        movieId?.let { movieViewModel.getRecommendedMovie(it) }
        movieId?.let { movieViewModel.getCastAndCrewByMovie(it) }
    }

    val movieDetailsState = movieViewModel.movieDetailsState.collectAsState().value
    val recommendedMovieState = movieViewModel.movieRecommendedMovieState.collectAsState().value
    val castByMovieState = movieViewModel.castByMovieState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MovieBannerTextBackground)
    ) {
        MovieDetailsImageOverlayText(
            movieDetails = movieDetailsState.movieDetails
        )

        ExpandableStory(movieDetailsState.movieDetails.overview)

        if (castByMovieState.castList.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(castByMovieState.castList) { item ->
                    CastItem(
                        castDetails = item
                    )
                }
            }
        }

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


@Preview
@Composable
private fun PreviewMovieDetailScreen() {
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
