package worldimage.watchme.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import worldimage.watchme.R
import worldimage.watchme.ui.theme.CategoryBackground
import worldimage.watchme.utils.formateToSuffix

@Composable
fun ReviewComponents(
    modifier: Modifier,
    voteAverage: Double,
    voteCount: Int
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier
                .padding(start = 14.dp, top = 4.dp, bottom = 4.dp, end = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(22.dp), // control size
                painter = painterResource(id = R.drawable.star),
                contentDescription = "IMDB"
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = voteAverage.toString(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 16.sp,
                            lineHeightStyle = LineHeightStyle(
                                alignment = LineHeightStyle.Alignment.Center,
                                trim = LineHeightStyle.Trim.Both
                            )
                        )
                    )
                    Text(
                        text = " /10",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White.copy(alpha = 0.5f),
                            lineHeight = 16.sp,
                            lineHeightStyle = LineHeightStyle(
                                alignment = LineHeightStyle.Alignment.Center,
                                trim = LineHeightStyle.Trim.Both
                            )
                        )
                    )
                }
                Text(
                    text = voteCount.formateToSuffix(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White.copy(alpha = 0.5f),
                        lineHeight = 12.sp,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        )
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewReviewComponents() {
    ReviewComponents(
        modifier = Modifier,
        voteAverage = 4.299,
        voteCount = 437
    )
}