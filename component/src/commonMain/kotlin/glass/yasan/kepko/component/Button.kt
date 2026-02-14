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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.border.borderStrokeFor
import glass.yasan.kepko.foundation.color.ProvideLocalContentColor
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import androidx.compose.material3.Button as Material3Button
import androidx.compose.material3.ButtonDefaults as Material3ButtonDefaults

@Composable
public fun Button(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.foreground,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
) {
    ProvideLocalContentColor(
        color = contentColor,
    ) {
        Material3Button(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            border = border,
            elevation = elevation,
            contentPadding = contentPadding,
            interactionSource = interactionSource,
            content = content,
            colors = Material3ButtonDefaults.buttonColors(
                containerColor = containerColor,
                disabledContainerColor = containerColor.copy(alpha = 0.50f),
                contentColor = contentColor,
                disabledContentColor = contentColor.copy(alpha = 0.70f),
            ),
        )
    }
}

@PreviewWithTest
@Composable
internal fun ButtonLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val containerColors = KepkoTheme.colors.getSemanticColors() +
            KepkoTheme.colors.foreground +
            KepkoTheme.colors.content

    Column(
        modifier = Modifier.background(KepkoTheme.colors.foreground)
    ) {
        containerColors.forEach { containerColor ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(
                    vertical = 2.dp,
                    horizontal = 4.dp,
                ),
            ) {
                Button(
                    onClick = {},
                    containerColor = containerColor,
                    content = {
                        Text(
                            text = "Button",
                            maxLines = 1,
                        )
                    },
                    modifier = Modifier.weight(1f),
                )
                Button(
                    onClick = {},
                    enabled = false,
                    containerColor = containerColor,
                    content = {
                        Text(
                            text = "Button",
                            maxLines = 1,
                        )
                    },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}
