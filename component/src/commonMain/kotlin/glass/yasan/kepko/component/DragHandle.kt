package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme

@Composable
public fun DragHandle(
    modifier: Modifier = Modifier,
    width: Dp = 64.dp,
    height: Dp = 4.dp,
    shape: Shape = KepkoTheme.shapes.extraLarge,
    color: Color = KepkoTheme.colors.contentSubtle,
    topDividerColor: Color = KepkoTheme.colors.outline,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = topDividerColor,
        )
        Box(
            Modifier
                .padding(vertical = 12.dp)
                .padding(horizontal = 16.dp)
                .size(
                    width = width,
                    height = height,
                )
                .clip(shape)
                .background(color)
        )
    }
}
