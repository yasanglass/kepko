package glass.yasan.kepko.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.color.ProvideLocalContentColor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import androidx.compose.material3.TextButton as Material3TextButton
import androidx.compose.material3.ButtonDefaults as Material3ButtonDefaults

@Composable
public fun TextButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    colors: TextButtonColors = TextButtonDefaults.colors(),
    enabled: Boolean = true,
    shape: Shape = KepkoTheme.shapes.extraLarge,
    border: BorderStroke? = null,
    elevation: ButtonElevation? = null,
    contentPadding: PaddingValues = TextButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
) {
    ProvideLocalContentColor(
        color = colors.contentColor,
    ) {
        Material3TextButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            border = border,
            elevation = elevation,
            contentPadding = contentPadding,
            interactionSource = interactionSource,
            content = content,
            colors = Material3ButtonDefaults.textButtonColors(
                containerColor = colors.containerColor,
                contentColor = colors.contentColor,
                disabledContainerColor = colors.disabledContainerColor,
                disabledContentColor = colors.disabledContentColor,
            ),
        )
    }
}

@PreviewWithTest
@Composable
internal fun TextButtonLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextButtonDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextButtonBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextButtonSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextButtonSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val contentColors = KepkoTheme.colors.getSemanticColors() +
            KepkoTheme.colors.content

    Column(
        modifier = Modifier.background(KepkoTheme.colors.foreground)
    ) {
        contentColors.forEach { contentColor ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(
                    vertical = 2.dp,
                    horizontal = 4.dp,
                ),
            ) {
                TextButton(
                    onClick = {},
                    colors = TextButtonDefaults.colors(contentColor = contentColor),
                    content = {
                        Text(
                            text = "TextButton",
                            maxLines = 1,
                        )
                    },
                    modifier = Modifier.weight(1f),
                )
                TextButton(
                    onClick = {},
                    enabled = false,
                    colors = TextButtonDefaults.colors(contentColor = contentColor),
                    content = {
                        Text(
                            text = "TextButton",
                            maxLines = 1,
                        )
                    },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}
