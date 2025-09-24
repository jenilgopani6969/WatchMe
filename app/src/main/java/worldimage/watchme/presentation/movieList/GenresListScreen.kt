package worldimage.watchme.presentation.movieList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import worldimage.watchme.domain.model.Genres
import worldimage.watchme.presentation.MovieViewModel
import worldimage.watchme.presentation.components.GenresButton
import worldimage.watchme.utils.Constant
import worldimage.watchme.utils.categoryTitleToApiName

@Composable
fun GenresHorizonalList(
    type: String,
    selectedCategory: String = "",
    categoryList: List<Genres>,
) {
    val viewModel: MovieViewModel = hiltViewModel()
    var selectedGenreId by remember {
        mutableStateOf(
            value = categoryList.firstOrNull {
                type == Constant.CATEGORY && it.name == selectedCategory
            }?.id ?: categoryList.firstOrNull()?.id
        )
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categoryList) { item ->
            val isSelected = item.id == selectedGenreId
            GenresButton(
                text = item.name,
                isSelected = isSelected,
                onClick = {
                    selectedGenreId = item.id
                    when(type) {
                        Constant.GENRE -> {
                            viewModel.getMovieListByGenres(
                                type = "movie",
                                genresId = item.id.toString(),
                                page = 1
                            )
                        }
                        Constant.CATEGORY -> {
                            viewModel.getMovieListByCategory(
                                category = item.name.categoryTitleToApiName(),
                                page = 1
                            )
                        }
                    }
                }
            )
        }
    }
}