package worldimage.watchme.domain.model

import worldimage.watchme.data.remote.dto.Genre

data class MovieDetails(
    val id: Int,
    val title: String,
    val poster_path: String,
    val genres: List<Genre>,
    val vote_average: Double,
    val vote_count: Int,
)