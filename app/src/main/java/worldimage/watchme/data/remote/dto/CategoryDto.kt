package worldimage.watchme.data.remote.dto

data class CategoryDto(
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String
)