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
): ViewModel() {

    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    fun getMovieByCategory(
        isForceFetchFromApi: Boolean,
        category: String,
        page: Int
    ) {
        if (isForceFetchFromApi) {
            viewModelScope.launch {
                movieRepository.getMoviesByCategory(
                    category = category,
                    page = page
                ).collectLatest { resource ->
                    when(resource) {
                        is Resource.Loading ->  {
                            _movieListState.update { it.copy(isLoading = resource.isLoading) }
                        }
                        is Resource.Success ->  {
                            resource.data?.let { movieList ->
                                _movieListState.update { it.copy(movieList = movieList) }
                            }
                        }
                        is Resource.Error ->  {
                            resource.message?.let { errorMessage ->
                                _movieListState.update { it.copy(errorMessage = errorMessage) }
                            }
                        }
                    }
                }
            }
        }
    }
}