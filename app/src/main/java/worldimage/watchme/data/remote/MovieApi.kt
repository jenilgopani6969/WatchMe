package worldimage.watchme.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import worldimage.watchme.data.remote.dto.GenresDto
import worldimage.watchme.data.remote.dto.MovieDetailsDto
import worldimage.watchme.data.remote.dto.MovieListDto

interface MovieApi {

    @GET("movie/{category}")
    suspend fun getMoviesListByCategory(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    @GET("discover/{type}")
    suspend fun getMoviesListByGenres(
        @Path("type") type: String,
        @Query("with_genres") genresId: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    @GET("genre/{type}/list")
    suspend fun getCategoryList(
        @Path("type") type: String,
        @Query("api_key") apiKey: String = API_KEY
    ): GenresDto

    @GET("movie/{movie_id}")
    suspend fun getMoviesDetails(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetailsDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = ""
    }
}
