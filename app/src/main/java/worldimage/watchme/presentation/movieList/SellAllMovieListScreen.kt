package worldimage.watchme.presentation.movieList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import worldimage.watchme.domain.model.Genres
import worldimage.watchme.navigation.Screen
import worldimage.watchme.presentation.MovieViewModel
import worldimage.watchme.presentation.components.MovieBanner
import worldimage.watchme.utils.Constant
import worldimage.watchme.utils.categoryTitleToApiName

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun SeeAllMovieListScreen(
    category: String?,
    movieViewModel: MovieViewModel,
    navController: NavController
) {
    val lazyGridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        category?.let {
            movieViewModel.getMovieListByCategory(
                category = it.categoryTitleToApiName(),
                page = 1
            )
        }
    }

    val movieListByPopularState = movieViewModel.movieListByPopularState.collectAsState().value
    val movieListByTopRatedState = movieViewModel.movieListByTopRatedState.collectAsState().value
    val movieListByUpcomingState = movieViewModel.movieListByUpcomingState.collectAsState().value
    val movieListByNowPlayingState = movieViewModel.movieListByNowPlayingState.collectAsState().value

    val latestState = listOf(movieListByPopularState, movieListByTopRatedState, movieListByUpcomingState, movieListByNowPlayingState).maxByOrNull { it.lastUpdated }

    Column {
        val categoryList = listOf(
            Genres(id = 0, name = Constant.NOW_PLAYING),
            Genres(id = 1, name = Constant.POPULAR),
            Genres(id = 2, name = Constant.UPCOMING),
            Genres(id = 3, name = Constant.TOP_RATED)
        )
        GenresHorizonalList(
            type = Constant.CATEGORY,
            selectedCategory = category.toString(),
            categoryList = categoryList
        )
        latestState?.movieList?.let {
            if (it.isNotEmpty()) {
                
                val configuration = LocalConfiguration.current
                val screenWidth = configuration.screenWidthDp.dp
                val itemMinWidth = 118.dp

                // calculate how many columns can fit
                val columns = (screenWidth / itemMinWidth).toInt().coerceAtLeast(2)

                if (it.size <= 20 && !latestState.isLoading) {
                    coroutineScope.launch {
                        lazyGridState.animateScrollToItem(0)
                    }
                }

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    columns = GridCells.Fixed(columns),
                    contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    state = lazyGridState
                ) {
                    itemsIndexed(latestState.movieList) { index, item ->
                        MovieBanner(
                            movieDetails = item,
                        ) {
                            navController.navigate(route = Screen.MovieDetails.route + "?movieId=${item.id}")
                        }
                    }
                }
            }
        }
    }
}



@Preview
@Composable
private fun PreviewSeeAllMovieListScreen() {
    //SeeAllMovieListScreen(category = "", navController = rememberNavController())
}