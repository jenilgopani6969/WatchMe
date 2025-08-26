package worldimage.watchme.data.repository

import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import worldimage.watchme.data.remote.MovieApi
import worldimage.watchme.domain.model.MovieDetails
import worldimage.watchme.domain.repository.MovieRepository
import worldimage.watchme.utils.Resource
import worldimage.watchme.utils.toMovieDetails
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
): MovieRepository {
    override suspend fun getMoviesByCategory(
        category: String,
        page: Int
    ): Flow<Resource<List<MovieDetails>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val movieResponse = movieApi.getMoviesListByCategory(
                    category = category,
                    page = page
                )
                emit(
                    Resource.Success(
                    data = movieResponse.results.map { movieDto ->
                        movieDto.toMovieDetails()
                    }
                ))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "Unknown Error!"))
            } catch (e: IOException) {
                emit(Resource.Error("Error while connecting to internet!"))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown Exception!"))
            }
            emit(Resource.Loading(false))
            return@flow
        }
    }
}