package worldimage.watchme.presentation

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
import worldimage.watchme.presentation.movieList.MovieListScreen
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
