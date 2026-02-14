package glass.yasan.kepko.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import androidx.compose.material3.HorizontalDivider as Material3HorizontalDivider

@Composable
public fun HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = KepkoTheme.colors.outline,
    thickness: Dp = KepkoTheme.dimensions.borderThickness,
) {
    Material3HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color,
    )
}

@PreviewWithTest
@Composable
internal fun HorizontalDividerPreview() {
    Column {
        KepkoTheme {
            Foreground(
                modifier = Modifier.padding(16.dp)
            ) {
                HorizontalDivider()
            }
        }
    }
}
