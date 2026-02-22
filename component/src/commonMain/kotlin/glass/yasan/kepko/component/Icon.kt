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
import glass.yasan.kepko.resource.Icons
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
            Icon(painter = Icons.add, contentDescription = null)
            Icon(painter = Icons.bugReport, contentDescription = null)
            Icon(painter = Icons.check, contentDescription = null)
            Icon(painter = Icons.chevronBackward, contentDescription = null)
            Icon(painter = Icons.chevronForward, contentDescription = null)
            Icon(painter = Icons.close, contentDescription = null)
            Icon(painter = Icons.code, contentDescription = null)
            Icon(painter = Icons.delete, contentDescription = null)
            Icon(painter = Icons.edit, contentDescription = null)
            Icon(painter = Icons.error, contentDescription = null)
            Icon(painter = Icons.favorite, contentDescription = null)
            Icon(painter = Icons.info, contentDescription = null)
            Icon(painter = Icons.lock, contentDescription = null)
            Icon(painter = Icons.lockOpen, contentDescription = null)
            Icon(painter = Icons.privacyTip, contentDescription = null)
            Icon(painter = Icons.settings, contentDescription = null)
            Icon(painter = Icons.star, contentDescription = null)
            Icon(painter = Icons.warning, contentDescription = null)
        }
    }
}
