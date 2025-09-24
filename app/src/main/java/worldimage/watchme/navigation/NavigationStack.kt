package worldimage.watchme.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import worldimage.watchme.presentation.MovieViewModel
import worldimage.watchme.presentation.moviedetails.MovieDetailScreen
import worldimage.watchme.presentation.movieList.MovieListScreen
import worldimage.watchme.presentation.movieList.SeeAllMovieListScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(
            route = Screen.MainScreen.route
        ) {
            val movieViewModel: MovieViewModel = hiltViewModel(it)
            MovieListScreen(
                navController = navController,
                movieViewModel = movieViewModel
            )
        }
        composable(
            route = Screen.MovieDetails.route + "?movieId={movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            val movieViewModel: MovieViewModel = hiltViewModel(it)
            MovieDetailScreen(
                navController = navController,
                movieViewModel = movieViewModel,
                movieId = it.arguments?.getString("movieId")
            )
        }
        composable(
            route = Screen.SeeAllMovieList.route + "?category={category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            val movieViewModel: MovieViewModel = hiltViewModel(it)
            SeeAllMovieListScreen(
                navController = navController,
                movieViewModel = movieViewModel,
                category = it.arguments?.getString("category")
            )
        }
    }
}
