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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.border.borderStrokeFor
import glass.yasan.kepko.foundation.color.ProvideLocalContentColor
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Button as Material3Button
import androidx.compose.material3.ButtonDefaults as Material3ButtonDefaults

@Composable
public fun Button(
    text: String,
    subtle: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    leadingContent: @Composable RowScope.() -> Unit = {},
    trailingContent: @Composable RowScope.() -> Unit = {},
) {
    val containerColor = if (subtle) KepkoTheme.colors.foreground else KepkoTheme.colors.content
    val contentColor: Color = contentColorFor(containerColor)

    Button(
        text = text,
        onClick = onClick,
        modifier = modifier,
        contentModifier = contentModifier,
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        shape = shape,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
    )
}

@Composable
public fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.content,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    textAlign: TextAlign = TextAlign.Center,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    leadingContent: @Composable RowScope.() -> Unit = {},
    trailingContent: @Composable RowScope.() -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        shape = shape,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = contentModifier,
            ) {
                leadingContent()
                Text(
                    text = text.uppercase(),
                    textAlign = textAlign,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                )
                trailingContent()
            }
        }
    )
}

@Suppress("LongParameterList")
@Composable
private fun Button(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    enabled: Boolean,
    shape: Shape,
    border: BorderStroke?,
    elevation: ButtonElevation?,
    contentPadding: PaddingValues,
    interactionSource: MutableInteractionSource?,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
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

@Preview
@Composable
private fun ButtonColorOptionsPreview() {
    KepkoTheme {
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
                        text = "Enabled",
                        onClick = {},
                        containerColor = containerColor,
                        modifier = Modifier.weight(1f),
                    )
                    Button(
                        text = "Disabled",
                        onClick = {},
                        enabled = false,
                        containerColor = containerColor,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ButtonSubtleOptionsPreview() {
    KepkoTheme {
        Column(
            modifier = Modifier.background(KepkoTheme.colors.foreground)
        ) {
            arrayOf(false, true).forEach { subtle ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(
                        vertical = 2.dp,
                        horizontal = 4.dp,
                    ),
                ) {
                    Button(
                        text = "Enabled",
                        onClick = {},
                        subtle = subtle,
                        modifier = Modifier.weight(1f),
                    )
                    Button(
                        text = "Disabled",
                        onClick = {},
                        enabled = false,
                        subtle = subtle,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}
