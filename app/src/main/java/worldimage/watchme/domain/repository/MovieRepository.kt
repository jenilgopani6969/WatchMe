package worldimage.watchme.domain.repository

import kotlinx.coroutines.flow.Flow
import worldimage.watchme.domain.model.Genres
import worldimage.watchme.domain.model.MovieDetails
import worldimage.watchme.utils.Resource

interface MovieRepository{
    suspend fun getMoviesByCategory(
        category: String,
        page: Int
    ): Flow<Resource<List<MovieDetails>>>

    suspend fun getMoviesByGenres(
        type: String,
        genresId: String,
        page: Int
    ): Flow<Resource<List<MovieDetails>>>

    suspend fun getCategoryList(
        type: String
    ): Flow<Resource<List<Genres>>>
}