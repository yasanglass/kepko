package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.color.contentColorFor

@ExperimentalKepkoApi
@Composable
public fun Pill(
    text: String,
    containerColor: Color,
    contentColor: Color = contentColorFor(containerColor),
    modifier: Modifier = Modifier,
) {
    Text(
        text = text.uppercase(),
        fontSize = 10.sp,
        color = contentColor,
        fontWeight = FontWeight.Medium,
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = containerColor)
            .padding(horizontal = 12.dp)
    )
}
