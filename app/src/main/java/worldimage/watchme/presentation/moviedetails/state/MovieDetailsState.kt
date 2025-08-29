package worldimage.watchme.presentation.moviedetails.state

import worldimage.watchme.domain.model.Genres
import worldimage.watchme.domain.model.MovieDetails

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val movieDetails: MovieDetails? = null
)