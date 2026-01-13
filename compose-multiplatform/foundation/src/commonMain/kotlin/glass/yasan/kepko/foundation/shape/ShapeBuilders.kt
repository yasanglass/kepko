package glass.yasan.kepko.foundation.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

public fun shapes(
    button: Shape = CircleShape,
): Shapes = Shapes(
    button = button,
)

@Preview
@Composable
private fun ShapesPreview() {
    KepkoTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(300.dp)
                    .clip(shape = KepkoTheme.shapes.containerOuter)
                    .background(KepkoTheme.colors.background),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(shape = KepkoTheme.shapes.container)
                        .background(KepkoTheme.colors.midground),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(shape = KepkoTheme.shapes.containerInner)
                            .background(KepkoTheme.colors.foreground),
                    ) {}
                }
            }
        }
    }
}
