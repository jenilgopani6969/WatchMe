package worldimage.watchme.presentation.movieList.state

import worldimage.watchme.domain.model.MovieList

data class MovieListByCategoryState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val movieList: List<MovieList> = emptyList()
)