package worldimage.watchme.presentation.movieList

import worldimage.watchme.domain.model.Genres

data class GenresState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val genresList: List<Genres> = emptyList()
)