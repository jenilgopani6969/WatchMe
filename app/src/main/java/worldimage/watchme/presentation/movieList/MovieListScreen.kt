package worldimage.watchme.presentation.movieList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MovieListScreen(modifier: Modifier) {

    val movieViewModel = hiltViewModel<MovieViewModel>()
    LaunchedEffect(Unit) {
        movieViewModel.getCategoryList(
            type = "movie"
        )
    }

    val movieListByGenresState = movieViewModel.movieListByGenresState.collectAsState().value
    val categoryListState = movieViewModel.categoryState.collectAsState().value

    Box(
        modifier = modifier
    ) {
        Column {
            if (categoryListState.categoryList.isNotEmpty()) {
                CategoryHorizonalList(categoryListState.categoryList)
                LaunchedEffect(Unit) {
                    movieViewModel.getMovieListByGenres(
                        type = "movie",
                        genresId = categoryListState.categoryList.first().id.toString(),
                        page = 1
                    )
                }
            }
            if (movieListByGenresState.movieList.isNotEmpty()) {
                MovieBannerRowListScreen(movieListByGenresState.movieList)
            }
        }

    }
}