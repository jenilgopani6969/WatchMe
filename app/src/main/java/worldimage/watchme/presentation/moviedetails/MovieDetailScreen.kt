package worldimage.watchme.presentation.moviedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import worldimage.watchme.presentation.MovieViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun MovieDetailScreen(
    movieId: String?
) {
    val movieViewModel = hiltViewModel<MovieViewModel>()
    LaunchedEffect(Unit) {
        movieId?.let { movieViewModel.getMovieDetails(it) }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Detail Screen")
        Text(movieViewModel.movieDetailsState.collectAsState().value.movieDetails?.title ?: "No Title")
    }
}