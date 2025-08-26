package worldimage.watchme.presentation.movieList

import worldimage.watchme.domain.model.MovieDetails

data class MovieListState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val movieList: List<MovieDetails> = emptyList()
)
