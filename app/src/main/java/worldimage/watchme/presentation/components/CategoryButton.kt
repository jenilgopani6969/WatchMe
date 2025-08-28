package worldimage.watchme.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import worldimage.watchme.ui.theme.CategoryBackground

@Composable
fun CategoryButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(36.dp)
            .wrapContentWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(CategoryBackground)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            text = text,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomButton() {
    CategoryButton(text = "Popular", onClick = {})
}
