package glass.yasan.kepko.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import glass.yasan.kepko.foundation.border.focusBorder
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons

@Composable
public fun IconButton(
    painter: Painter,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    outerPadding: PaddingValues = IconButtonDefaults.OuterPadding,
    innerPadding: PaddingValues = IconButtonDefaults.InnerPadding,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = LocalIndication.current,
) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        tint = if (enabled) tint else KepkoTheme.colors.contentDisabled,
        modifier = modifier
            .padding(outerPadding)
            .focusBorder(KepkoTheme.shapes.extraLarge)
            .clip(KepkoTheme.shapes.extraLarge)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                onClickLabel = onClickLabel,
                interactionSource = interactionSource,
                indication = indication,
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
    tint: Color = LocalContentColor.current,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    outerPadding: PaddingValues = IconButtonDefaults.OuterPadding,
    innerPadding: PaddingValues = IconButtonDefaults.InnerPadding,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = LocalIndication.current,
) {
    Icon(
        bitmap = bitmap,
        contentDescription = contentDescription,
        tint = if (enabled) tint else KepkoTheme.colors.contentDisabled,
        modifier = modifier
            .padding(outerPadding)
            .focusBorder(KepkoTheme.shapes.extraLarge)
            .clip(KepkoTheme.shapes.extraLarge)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                onClickLabel = onClickLabel,
                interactionSource = interactionSource,
                indication = indication,
            )
            .padding(innerPadding),
    )
}

@PreviewWithTest
@Composable
internal fun IconButtonLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonFocusedLightPreview() {
    KepkoTheme(palette = LIGHT) { FocusedPreviewIconButtonContent() }
}

@PreviewWithTest
@Composable
internal fun IconButtonFocusedDarkPreview() {
    KepkoTheme(palette = DARK) { FocusedPreviewIconButtonContent() }
}

@Composable
private fun FocusedPreviewIconButtonContent() {
    Midground {
        Row {
            IconButton(
                painter = Icons.settings,
                contentDescription = null,
                onClick = {},
                modifier = Modifier.focusRequester(rememberPreviewFocusRequester()),
            )
            IconButton(painter = Icons.info, contentDescription = null, onClick = {})
        }
    }
}

@Composable
private fun PreviewIconButtonContent() {
    Midground {
        Column {
            Row {
                IconButton(painter = Icons.info, contentDescription = null, onClick = {})
                IconButton(painter = Icons.settings, contentDescription = null, onClick = {})
                IconButton(painter = Icons.close, contentDescription = null, onClick = {})
            }
            Row {
                IconButton(
                    painter = Icons.info,
                    contentDescription = null,
                    onClick = {},
                    enabled = false,
                )
                IconButton(
                    painter = Icons.settings,
                    contentDescription = null,
                    onClick = {},
                    enabled = false,
                )
                IconButton(
                    painter = Icons.close,
                    contentDescription = null,
                    onClick = {},
                    enabled = false,
                )
            }
        }
    }
}
