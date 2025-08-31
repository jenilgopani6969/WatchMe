package worldimage.watchme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import worldimage.watchme.presentation.moviedetails.MovieDetailScreen
import worldimage.watchme.presentation.movieList.MovieListScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(
            route = Screen.MainScreen.route
        ) {
            MovieListScreen(
                navController = navController
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
            MovieDetailScreen(
                navController = navController,
                movieId = it.arguments?.getString("movieId")
            )
        }
    }
}
