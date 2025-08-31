package worldimage.watchme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import worldimage.watchme.domain.repository.MovieRepository
import worldimage.watchme.presentation.movieList.state.GenresState
import worldimage.watchme.presentation.movieList.state.MovieListByCategoryState
import worldimage.watchme.presentation.moviedetails.MovieDetailScreen
import worldimage.watchme.presentation.moviedetails.state.MovieDetailsState
import worldimage.watchme.utils.Constant
import worldimage.watchme.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movieListByPopularState = MutableStateFlow(MovieListByCategoryState())
    val movieListByPopularState = _movieListByPopularState.asStateFlow()

    private var _movieListByNowPlayingState = MutableStateFlow(MovieListByCategoryState())
    val movieListByNowPlayingState = _movieListByNowPlayingState.asStateFlow()

    private var _movieListByTopRatedState = MutableStateFlow(MovieListByCategoryState())
    val movieListByTopRatedState = _movieListByTopRatedState.asStateFlow()

    private var _movieListByUpcomingState = MutableStateFlow(MovieListByCategoryState())
    val movieListByUpcomingState = _movieListByUpcomingState.asStateFlow()

    private var _movieListByGenresState = MutableStateFlow(MovieListByCategoryState())
    val movieListByGenresState = _movieListByGenresState.asStateFlow()

    private var _genresState = MutableStateFlow(GenresState())
    val genresState = _genresState.asStateFlow()

    private var _movieDetailsState = MutableStateFlow(MovieDetailsState())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private var _movieRecommendedMovieState = MutableStateFlow(MovieListByCategoryState())
    val movieRecommendedMovieState = _movieRecommendedMovieState.asStateFlow()

    fun getMovieListByCategory(
        category: String,
        page: Int
    ) {
        viewModelScope.launch {
            movieRepository.getMoviesByCategory(
                category = category,
                page = page
            ).collectLatest { resource ->

                // map category to its state flow
                val stateFlow = when (category) {
                    Constant.POPULAR -> _movieListByPopularState
                    Constant.UPCOMING -> _movieListByUpcomingState
                    Constant.NOW_PLAYING -> _movieListByNowPlayingState
                    Constant.TOP_RATED -> _movieListByTopRatedState
                    else -> null
                }

                stateFlow?.let {
                    when (resource) {
                        is Resource.Loading -> {
                            stateFlow.update { it.copy(isLoading = resource.isLoading) }
                        }

                        is Resource.Success -> {
                            resource.data?.let { movieList ->
                                stateFlow.update { it.copy(movieList = movieList) }
                            }
                        }

                        is Resource.Error -> {
                            resource.message?.let { errorMessage ->
                                stateFlow.update { it.copy(errorMessage = errorMessage) }
                            }
                        }
                    }
                }
            }
        }
    }

    fun getMovieListByGenres(
        type: String,
        genresId: String,
        page: Int
    ) {
        viewModelScope.launch {
            movieRepository.getMoviesByGenres(
                type = type,
                genresId = genresId,
                page = page
            ).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _movieListByGenresState.update { it.copy(isLoading = resource.isLoading) }
                    }

                    is Resource.Success -> {
                        resource.data?.let { movieList ->
                            _movieListByGenresState.update { it.copy(movieList = movieList) }
                        }
                    }

                    is Resource.Error -> {
                        resource.message?.let { errorMessage ->
                            _movieListByGenresState.update { it.copy(errorMessage = errorMessage) }
                        }
                    }
                }
            }
        }
    }

    fun getCategoryList(
        type: String
    ) {
        viewModelScope.launch {
            movieRepository.getCategoryList(
                type = type
            ).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _genresState.update { it.copy(isLoading = resource.isLoading) }
                    }

                    is Resource.Success -> {
                        resource.data?.let { genresList ->
                            _genresState.update { it.copy(genresList = genresList) }
                        }
                    }

                    is Resource.Error -> {
                        resource.message?.let { errorMessage ->
                            _genresState.update { it.copy(errorMessage = errorMessage) }
                        }
                    }
                }
            }
        }
    }

    fun getMovieDetails(
        movieId: String
    ) {
        viewModelScope.launch {
            movieRepository.getMovieDetails(
                movieId = movieId
            ).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _movieDetailsState.update { it.copy(isLoading = resource.isLoading) }
                    }

                    is Resource.Success -> {
                        resource.data?.let { movieDetails ->
                            _movieDetailsState.update { it.copy(movieDetails = movieDetails) }
                        }
                    }

                    is Resource.Error -> {
                        resource.message?.let { errorMessage ->
                            _movieDetailsState.update { it.copy(errorMessage = errorMessage) }
                        }
                    }
                }
            }
        }
    }

    fun getRecommendedMovie(
        movieId: String
    ) {
        viewModelScope.launch {
            movieRepository.getRecommendedMovie(
                movieId = movieId
            ).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _movieRecommendedMovieState.update { it.copy(isLoading = resource.isLoading) }
                    }

                    is Resource.Success -> {
                        resource.data?.let { movieList ->
                            _movieRecommendedMovieState.update { it.copy(movieList = movieList) }
                        }
                    }

                    is Resource.Error -> {
                        resource.message?.let { errorMessage ->
                            _movieRecommendedMovieState.update { it.copy(errorMessage = errorMessage) }
                        }
                    }
                }
            }
        }
    }
}