package worldimage.watchme.presentation.movieList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import worldimage.watchme.domain.model.Category
import worldimage.watchme.presentation.components.CategoryButton

@Composable
fun CategoryHorizonalList(
    categoryList: List<Category>,
) {
    val viewModel: MovieViewModel = hiltViewModel()
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categoryList) { item ->
            CategoryButton(
                text = item.name,
                onClick = {
                    viewModel.getMovieListByGenres(
                        type = "movie",
                        genresId = item.id.toString(),
                        page = 1
                    )
                }
            )
        }
    }
}