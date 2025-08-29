package worldimage.watchme.presentation.movieList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import worldimage.watchme.domain.model.Genres
import worldimage.watchme.presentation.components.GenresButton

@Composable
fun GenresHorizonalList(
    categoryList: List<Genres>,
) {
    val viewModel: MovieViewModel = hiltViewModel()
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categoryList) { item ->
            GenresButton(
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