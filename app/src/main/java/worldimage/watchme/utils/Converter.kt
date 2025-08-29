package worldimage.watchme.utils

import worldimage.watchme.data.remote.dto.Genre
import worldimage.watchme.data.remote.dto.MovieDetailsDto
import worldimage.watchme.domain.model.Genres
import worldimage.watchme.domain.model.MovieDetails
import worldimage.watchme.domain.model.MovieList

fun MovieDetailsDto.toMovieList(): MovieList {
    return MovieList(
        id = this.id ?: 0,
        poster_path = this.poster_path ?: "",
        release_date = this.release_date ?: "",
        title = this.title ?: ""
    )
}

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = this.id ?: 0,
        title = this.title ?: "",
        poster_path = this.poster_path ?: "",
        genres = this.genres ?: emptyList<Genre>(),
        vote_average = this.vote_average ?: 0.0,
        vote_count = this.vote_count ?: 0
    )
}

fun Genre.toGenres(): Genres {
    return Genres(
        id = this.id,
        name = this.name
    )
}

fun String.toYear(): String {
    return this.take(4)
}