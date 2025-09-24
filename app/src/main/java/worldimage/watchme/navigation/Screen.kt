package worldimage.watchme.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object MovieDetails: Screen("movie_details")
    object SeeAllMovieList: Screen("see_all_movie_list")
}