package glass.yasan.kepko.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.resource.Icons

@Composable
public fun IconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    outerPadding: PaddingValues = IconButtonDefaults.OuterPadding,
    innerPadding: PaddingValues = IconButtonDefaults.InnerPadding,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        color = color,
        modifier = modifier
            .padding(outerPadding)
            .clip(CircleShape)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                onClickLabel = onClickLabel,
            )
            .padding(innerPadding),
    )
}

@Composable
public fun IconButton(
    painter: Painter,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    outerPadding: PaddingValues = IconButtonDefaults.OuterPadding,
    innerPadding: PaddingValues = IconButtonDefaults.InnerPadding,
) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        color = color,
        modifier = modifier
            .padding(outerPadding)
            .clip(CircleShape)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                onClickLabel = onClickLabel,
            )
            .padding(innerPadding),
    )
}

@Composable
public fun IconButton(
    bitmap: ImageBitmap,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    outerPadding: PaddingValues = IconButtonDefaults.OuterPadding,
    innerPadding: PaddingValues = IconButtonDefaults.InnerPadding,
) {
    Icon(
        bitmap = bitmap,
        contentDescription = contentDescription,
        color = color,
        modifier = modifier
            .padding(outerPadding)
            .clip(CircleShape)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                onClickLabel = onClickLabel,
            )
            .padding(innerPadding),
    )
}

@PreviewWithTest
@Composable
internal fun IconButtonLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewIconButtonContent() }
}

@Composable
private fun PreviewIconButtonContent() {
    Midground {
        Row {
            IconButton(painter = Icons.info, contentDescription = null, onClick = {})
            IconButton(painter = Icons.settings, contentDescription = null, onClick = {})
            IconButton(painter = Icons.close, contentDescription = null, onClick = {})
        }
    }
}
