package worldimage.watchme.presentation.movieList

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import worldimage.watchme.R
import worldimage.watchme.domain.model.MovieList
import worldimage.watchme.navigation.Screen
import worldimage.watchme.presentation.components.MovieBanner
import worldimage.watchme.utils.Constant

@SuppressLint("CoroutineCreationDuringComposition", "ConfigurationScreenWidthHeight")
@Composable
fun MovieListByCategoryScreen(
    modifier: Modifier = Modifier,
    movieList: List<MovieList>,
    isShowTitle: Boolean = false,
    title: String = "",
    isSellAllVisible: Boolean = true,
    navController: NavController,
    isPopBackStack: Boolean = false,
) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        if (isShowTitle) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (isSellAllVisible) {
                    Text(
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable(true, onClick = {
                                navController.navigate(route = Screen.SeeAllMovieList.route + "?category=${title}")
                            }),
                        text = "See all",
                        color = Color.White.copy(alpha = 0.5f),
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        } else {
            coroutineScope.launch {
                lazyListState.animateScrollToItem(0)
            }
        }

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            state = lazyListState
        ) {
            items(movieList) { item ->
                MovieBanner(
                    modifier = Modifier.width(132.dp),
                    movieDetails = item,
                ) {
                    if (isPopBackStack) {
                        navController.popBackStack()
                    }
                    navController.navigate(route = Screen.MovieDetails.route + "?movieId=${item.id}")
                }
            }
        }
    }
}