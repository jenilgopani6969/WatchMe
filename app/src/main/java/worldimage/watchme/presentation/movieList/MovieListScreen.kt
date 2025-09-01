package worldimage.watchme.presentation.movieList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import worldimage.watchme.R
import worldimage.watchme.presentation.MovieViewModel
import worldimage.watchme.utils.Constant

@Composable
fun MovieListScreen(
    navController: NavController,
) {
    val movieViewModel = hiltViewModel<MovieViewModel>()
    LaunchedEffect(Unit) {
        movieViewModel.getCategoryList(
            type = "movie"
        )
        movieViewModel.getMovieListByCategory(
            category = Constant.POPULAR,
            page = 1
        )
        movieViewModel.getMovieListByCategory(
            category = Constant.NOW_PLAYING,
            page = 1
        )
        movieViewModel.getMovieListByCategory(
            category = Constant.UPCOMING,
            page = 1
        )
        movieViewModel.getMovieListByCategory(
            category = Constant.TOP_RATED,
            page = 1
        )
    }

    val movieListByGenresState = movieViewModel.movieListByGenresState.collectAsState().value
    val movieListByPopularState = movieViewModel.movieListByPopularState.collectAsState().value
    val movieListByTopRatedState = movieViewModel.movieListByTopRatedState.collectAsState().value
    val movieListByUpcomingState = movieViewModel.movieListByUpcomingState.collectAsState().value
    val movieListByNowPlayingState = movieViewModel.movieListByNowPlayingState.collectAsState().value
    val genresListState = movieViewModel.genresState.collectAsState().value

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        if (genresListState.genresList.isNotEmpty()) {
            GenresHorizonalList(genresListState.genresList)
            LaunchedEffect(Unit) {
                movieViewModel.getMovieListByGenres(
                    type = "movie",
                    genresId = genresListState.genresList.first().id.toString(),
                    page = 1
                )
            }
        }
        if (movieListByGenresState.movieList.isNotEmpty()) {
            MovieListByCategoryScreen(
                movieList = movieListByGenresState.movieList,
                navController = navController
            )
        }
        if (movieListByPopularState.movieList.isNotEmpty()) {
            MovieListByCategoryScreen(
                movieList = movieListByPopularState.movieList,
                isShowTitle = true,
                title = stringResource(R.string.popular),
                navController = navController
            )
        }
        if (movieListByTopRatedState.movieList.isNotEmpty()) {
            MovieListByCategoryScreen(
                movieList = movieListByTopRatedState.movieList,
                isShowTitle = true,
                title = stringResource(R.string.top_rated),
                navController = navController
            )
        }
        if (movieListByUpcomingState.movieList.isNotEmpty()) {
            MovieListByCategoryScreen(
                movieList = movieListByUpcomingState.movieList,
                isShowTitle = true,
                title = stringResource(R.string.upcoming),
                navController = navController
            )
        }
        if (movieListByNowPlayingState.movieList.isNotEmpty()) {
            MovieListByCategoryScreen(
                modifier = Modifier.padding(bottom = 16.dp),
                movieList = movieListByNowPlayingState.movieList,
                isShowTitle = true,
                title = stringResource(R.string.now_playing),
                navController = navController
            )
        }
    }
}
