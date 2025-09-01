package worldimage.watchme.utils

import worldimage.watchme.data.remote.dto.CastItemDto
import worldimage.watchme.data.remote.dto.Genre
import worldimage.watchme.data.remote.dto.MovieDetailsDto
import worldimage.watchme.domain.model.CastDetails
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
        genres = this.genres ?: emptyList(),
        vote_average = this.vote_average ?: 0.0,
        vote_count = this.vote_count ?: 0,
        overview = this.overview ?: "",
        release_date = this.release_date ?: "",
        runtime = this.runtime ?: 0,
        backdrop_path = this.backdrop_path ?: ""
    )
}

fun CastItemDto.toCastDetails(): CastDetails {
    return CastDetails(
        cast_id = this.cast_id ?: 0,
        profile_path = this.profile_path ?: "",
        name = this.name ?: ""
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

fun String.toFirstAndLastName(): List<String> {
    return this.trim().split(" ")
}

fun Int.toHourAndMinute(): String {
    val hour = this / 60
    val minute = (this.toDouble() / 60 - hour) * 60
    return hour.toString() + "h " + minute.toInt().toString() + "m"
}

fun Int.formateToSuffix(): String {
    val withSuffix: String = if (this.toDouble() >= 1000) {
        (this.toDouble() / 1000).toString().take(3) + "k"
    } else this.toString()
    return withSuffix
}