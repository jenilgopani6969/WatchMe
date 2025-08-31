package worldimage.watchme.data.repository

import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import worldimage.watchme.data.remote.MovieApi
import worldimage.watchme.domain.model.Genres
import worldimage.watchme.domain.model.MovieDetails
import worldimage.watchme.domain.model.MovieList
import worldimage.watchme.domain.repository.MovieRepository
import worldimage.watchme.utils.Resource
import worldimage.watchme.utils.toGenres
import worldimage.watchme.utils.toMovieDetails
import worldimage.watchme.utils.toMovieList
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
): MovieRepository {
    override suspend fun getMoviesByCategory(
        category: String,
        page: Int
    ): Flow<Resource<List<MovieList>>> {
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
                        movieDto.toMovieList()
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

    override suspend fun getMoviesByGenres(
        type: String,
        genresId: String,
        page: Int
    ): Flow<Resource<List<MovieList>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val movieResponse = movieApi.getMoviesListByGenres(
                    type = type,
                    genresId = genresId,
                    page = page
                )
                emit(
                    Resource.Success(
                        data = movieResponse.results.map { movieDto ->
                            movieDto.toMovieList()
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

    override suspend fun getCategoryList(
        type: String
    ): Flow<Resource<List<Genres>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val movieResponse = movieApi.getCategoryList(
                    type = type
                )
                emit(
                    Resource.Success(
                        data = movieResponse.genres.map { genre ->
                            genre.toGenres()
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

    override suspend fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val movieResponse = movieApi.getMoviesDetails(
                    movieId = movieId
                )
                emit(Resource.Success(
                    movieResponse.toMovieDetails()
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

    override suspend fun getRecommendedMovie(movieId: String): Flow<Resource<List<MovieList>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val movieResponse = movieApi.getRecommendedMovie(
                    movieId = movieId
                )
                emit(
                    Resource.Success(
                        data = movieResponse.results.map { movieDto ->
                            movieDto.toMovieList()
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