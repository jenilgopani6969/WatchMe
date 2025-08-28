package worldimage.watchme.presentation.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import worldimage.watchme.domain.repository.MovieRepository
import worldimage.watchme.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movieListByCategoryState = MutableStateFlow(MovieListState())
    val movieListByCategoryState = _movieListByCategoryState.asStateFlow()

    private var _movieListByGenresState = MutableStateFlow(MovieListState())
    val movieListByGenresState = _movieListByGenresState.asStateFlow()

    private var _categoryState = MutableStateFlow(CategoryState())
    val categoryState = _categoryState.asStateFlow()

    fun getMovieListByCategory(
        category: String,
        page: Int
    ) {
        viewModelScope.launch {
            movieRepository.getMoviesByCategory(
                category = category,
                page = page
            ).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _movieListByCategoryState.update { it.copy(isLoading = resource.isLoading) }
                    }

                    is Resource.Success -> {
                        resource.data?.let { movieList ->
                            _movieListByCategoryState.update { it.copy(movieList = movieList) }
                        }
                    }

                    is Resource.Error -> {
                        resource.message?.let { errorMessage ->
                            _movieListByCategoryState.update { it.copy(errorMessage = errorMessage) }
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
                        _categoryState.update { it.copy(isLoading = resource.isLoading) }
                    }

                    is Resource.Success -> {
                        resource.data?.let { categoryList ->
                            _categoryState.update { it.copy(categoryList = categoryList) }
                        }
                    }

                    is Resource.Error -> {
                        resource.message?.let { errorMessage ->
                            _categoryState.update { it.copy(errorMessage = errorMessage) }
                        }
                    }
                }
            }
        }
    }
}