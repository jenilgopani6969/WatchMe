package worldimage.watchme.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import worldimage.watchme.R
import worldimage.watchme.data.remote.MovieApi
import worldimage.watchme.domain.model.CastDetails
import worldimage.watchme.ui.theme.CategoryBackground
import worldimage.watchme.ui.theme.MovieBannerTextBackground
import worldimage.watchme.utils.toFirstAndLastName

@Composable
fun CastItem(
    castDetails: CastDetails
) {
    Column(
        modifier = Modifier
            .width(64.dp)
            .background(MovieBannerTextBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(8.dp))
                .background(CategoryBackground),
            contentAlignment = Alignment.Center
        ) {
            val imageUrl = MovieApi.IMAGE_URL + castDetails.profile_path
            AsyncImage(
                model = imageUrl,
                contentDescription = " image",
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder_imageloading),
                error = painterResource(R.drawable.placeholder_imagenotfound)
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 4.dp),
            text = castDetails.name.toFirstAndLastName().first(),
            color = Color.White.copy(alpha = 0.5f),
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                color = Color.White.copy(alpha = 0.5f),
                lineHeight = 12.sp,
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.Both
                ),
            ),
            maxLines = 1
        )
        Text(
            text = castDetails.name.toFirstAndLastName().last(),
            color = Color.White.copy(alpha = 0.5f),
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                color = Color.White.copy(alpha = 0.5f),
                lineHeight = 12.sp,
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.Both
                )
            ),
            maxLines = 1
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PrevCastItem() {
    CastItem(
        castDetails = CastDetails(
            cast_id = 0,
            profile_path = "",
            name = "Tom Cruize"
        )
    )
}
