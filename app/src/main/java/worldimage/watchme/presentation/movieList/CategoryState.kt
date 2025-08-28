package worldimage.watchme.presentation.movieList

import worldimage.watchme.domain.model.Category

data class CategoryState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val categoryList: List<Category> = emptyList()
)