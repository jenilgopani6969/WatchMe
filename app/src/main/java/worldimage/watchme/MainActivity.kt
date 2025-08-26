package worldimage.watchme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import worldimage.watchme.presentation.movieList.MovieViewModel
import worldimage.watchme.ui.theme.WatchMeTheme
import worldimage.watchme.utils.Constant

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WatchMeTheme {
                val movieViewModel = hiltViewModel<MovieViewModel>()
                LaunchedEffect(Unit) {
                    movieViewModel.getMovieByCategory(
                        isForceFetchFromApi = true,
                        category = Constant.POPULAR,
                        page = 1
                    )
                }

                val movieListState = movieViewModel.movieListState.collectAsState().value

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (movieListState.isLoading) {
                        Greeting(
                            name = "Loading",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                    if (movieListState.movieList.isNotEmpty()){
                        Greeting(
                            name = movieListState.movieList.first().title,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WatchMeTheme {
        Greeting("Android")
    }
}