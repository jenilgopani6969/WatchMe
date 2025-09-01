package worldimage.watchme.presentation.moviedetails.state

import worldimage.watchme.data.remote.dto.Genre
import worldimage.watchme.domain.model.MovieDetails

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val movieDetails: MovieDetails = MovieDetails(
        id = 1,
        poster_path = "/ombsmhYUqR4qqOLOxAyr5V8hbyv.jpg",
        title = "Superman",
        vote_average = 7.555,
        vote_count = 423,
        genres = listOf(Genre(id = 1, name = "Action")),
        release_date = "2025-07-29",
        overview = "",
        runtime = 93,
        backdrop_path = ""
    )
)