package worldimage.watchme.data.remote.dto

data class MovieListDto(
    val page: Int,
    val results: List<MovieDetailsDto>,
    val total_pages: Int,
    val total_results: Int
)