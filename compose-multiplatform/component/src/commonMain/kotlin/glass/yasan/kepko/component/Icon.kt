package glass.yasan.kepko.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import androidx.compose.material3.Icon as Material3Icon

@Composable
public fun Icon(
    imageVector: ImageVector,
    contentDescription: String?,
    color: Color = LocalContentColor.current,
    modifier: Modifier = Modifier,
    size: Dp = KepkoTheme.dimensions.iconSize,
) {
    Material3Icon(
        imageVector = imageVector,
        tint = color,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size),
    )
}

@Composable
public fun Icon(
    painter: Painter,
    contentDescription: String?,
    color: Color = LocalContentColor.current,
    modifier: Modifier = Modifier,
    size: Dp = KepkoTheme.dimensions.iconSize,
) {
    Material3Icon(
        painter = painter,
        tint = color,
        contentDescription = contentDescription,
        modifier = modifier
            .size(24.dp),
    )
}

@Composable
public fun Icon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    color: Color = LocalContentColor.current,
    modifier: Modifier = Modifier,
    size: Dp = KepkoTheme.dimensions.iconSize,
) {
    Material3Icon(
        bitmap = bitmap,
        tint = color,
        contentDescription = contentDescription,

        modifier = modifier
            .size(24.dp),
    )
}

@PreviewWithTest
@Composable
internal fun IconLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun IconDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun IconBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun IconSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun IconSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    Midground {
        Row {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_asterisk),
                contentDescription = "Asterisk",
            )
            Icon(
                painter = painterResource(Res.drawable.ic_asterisk),
                contentDescription = "Asterisk",
            )
            Icon(
                bitmap = imageResource(Res.drawable.ic_emergency),
                contentDescription = "Emergency",
            )
        }
    }
}
