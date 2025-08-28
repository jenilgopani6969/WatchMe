package worldimage.watchme.utils

import worldimage.watchme.data.remote.dto.CategoryDto
import worldimage.watchme.data.remote.dto.Genre
import worldimage.watchme.data.remote.dto.MovieDto
import worldimage.watchme.domain.model.Category
import worldimage.watchme.domain.model.MovieDetails

fun MovieDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        backdrop_path = this.backdrop_path ?: "",
        id = this.id ?: 0,
        original_language = this.original_language ?: "",
        original_title = this.original_title ?: "",
        overview = this.overview ?: "",
        popularity = this.popularity ?: 0.0,
        poster_path = this.poster_path ?: "",
        release_date = this.release_date ?: "",
        title = this.title ?: "",
        vote_average = this.vote_average ?: 0.0,
        vote_count = this.vote_count ?: 0
    )
}

fun Genre.toCategory(): Category {
    return Category(
        id = this.id,
        name = this.name
    )
}

fun String.toYear(): String {
    return this.take(4)
}