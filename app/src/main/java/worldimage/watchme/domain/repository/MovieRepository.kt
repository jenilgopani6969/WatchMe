package worldimage.watchme.domain.repository

import kotlinx.coroutines.flow.Flow
import worldimage.watchme.domain.model.MovieDetails
import worldimage.watchme.utils.Resource

interface MovieRepository{
    suspend fun getMoviesByCategory(
        category: String,
        page: Int
    ): Flow<Resource<List<MovieDetails>>>
}