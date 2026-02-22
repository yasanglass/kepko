package glass.yasan.kepko.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.resource.Strings
import androidx.compose.material3.Surface as Material3Surface

@ExperimentalKepkoApi
@Composable
public fun Surface(
    color: Color,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    contentColor: Color = contentColorFor(containerColor = color),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit,
) {
    Material3Surface(
        modifier = modifier,
        shape = shape,
        color = color,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border,
        content = content,
    )
}

@ExperimentalKepkoApi
@Composable
public fun Foreground(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    contentColor: Color = contentColorFor(containerColor = KepkoTheme.colors.foreground),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = KepkoTheme.colors.foreground,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border,
        content = content,
    )
}

@ExperimentalKepkoApi
@Composable
public fun Midground(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    contentColor: Color = contentColorFor(containerColor = KepkoTheme.colors.midground),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = KepkoTheme.colors.midground,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border,
        content = content,
    )
}

@ExperimentalKepkoApi
@Composable
public fun Background(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    contentColor: Color = contentColorFor(containerColor = KepkoTheme.colors.background),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = KepkoTheme.colors.background,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border,
        content = content,
    )
}

@PreviewWithTest
@Composable
internal fun SurfaceLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { SurfacePreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SurfaceDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { SurfacePreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SurfaceBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { SurfacePreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SurfaceSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { SurfacePreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SurfaceSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { SurfacePreviewContent() }
}

@OptIn(ExperimentalKepkoApi::class)
@Composable
private fun SurfacePreviewContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp),
    ) {
        Foreground(modifier = Modifier.padding(8.dp)) {
            Text(text = Strings.themeStyleLight)
        }
        Midground(modifier = Modifier.padding(8.dp)) {
            Text(text = Strings.themeStyleDark)
        }
        Background(modifier = Modifier.padding(8.dp)) {
            Text(text = Strings.themeStyleBlack)
        }
    }
}
