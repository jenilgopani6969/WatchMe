package worldimage.watchme.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import worldimage.watchme.data.remote.dto.Genre
import worldimage.watchme.ui.theme.CategoryBackground

@Composable
fun MovieDetailsCategoryItem(
    genre: Genre,
) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .background(CategoryBackground),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier
                    .height(5.dp)
                    .width(5.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.Red),
            )

            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = genre.name,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieDetailsCategoryItem() {
    val genre = Genre(id = 878, name = "Science Fiction")
    MovieDetailsCategoryItem(genre = genre)
}
