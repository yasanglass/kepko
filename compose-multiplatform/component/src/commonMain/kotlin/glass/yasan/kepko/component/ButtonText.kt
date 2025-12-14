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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.border.borderStrokeFor
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun ButtonText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.content,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = KepkoTheme.shapes.button,
    textAlign: TextAlign = TextAlign.Start,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonTextDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    leadingIcon: Painter?,
    trailingIcon: Painter?,
) {
    ButtonText(
        text = text,
        onClick = onClick,
        modifier = modifier,
        contentModifier = contentModifier,
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        shape = shape,
        textAlign = textAlign,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        fillWidth = fillWidth,
        leadingContent = {
            leadingIcon?.let { painter ->
                Icon(
                    painter = painter,
                    contentDescription = null,
                )
            }
        },
        trailingContent = {
            trailingIcon?.let { painter ->
                Icon(
                    painter = painter,
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
public fun ButtonText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.content,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = KepkoTheme.shapes.button,
    textAlign: TextAlign = TextAlign.Start,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonTextDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    leadingIcon: Painter?,
) {
    ButtonText(
        text = text,
        onClick = onClick,
        modifier = modifier,
        contentModifier = contentModifier,
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        shape = shape,
        textAlign = textAlign,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        fillWidth = fillWidth,
        leadingContent = {
            leadingIcon?.let { painter ->
                Icon(
                    painter = painter,
                    contentDescription = null,
                )
            }
        },
        trailingContent = {},
    )
}

@Composable
public fun ButtonText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.content,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = KepkoTheme.shapes.button,
    textAlign: TextAlign = TextAlign.Start,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonTextDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    leadingIcon: ImageVector?,
) {
    ButtonText(
        text = text,
        onClick = onClick,
        modifier = modifier,
        contentModifier = contentModifier,
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        shape = shape,
        textAlign = textAlign,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        fillWidth = fillWidth,
        leadingContent = {
            leadingIcon?.let { imageVector ->
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                )
            }
        },
        trailingContent = {},
    )
}

@Composable
public fun ButtonText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.content,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = KepkoTheme.shapes.button,
    textAlign: TextAlign = TextAlign.Start,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonTextDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector?,
) {
    ButtonText(
        text = text,
        onClick = onClick,
        modifier = modifier,
        contentModifier = contentModifier,
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        shape = shape,
        textAlign = textAlign,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        fillWidth = fillWidth,
        leadingContent = {
            leadingIcon?.let { imageVector ->
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                )
            }
        },
        trailingContent = {
            trailingIcon?.let { imageVector ->
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
public fun ButtonText(
    text: String,
    onClick: () -> Unit,
    leadingContent: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.content,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = KepkoTheme.shapes.button,
    textAlign: TextAlign = TextAlign.Start,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonTextDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    trailingContent: @Composable RowScope.() -> Unit = {},
) {
    Button(
        onClick = onClick,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .then(contentModifier),
            ) {
                leadingContent()
                Text(
                    text = text.uppercase(),
                    textAlign = textAlign,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    modifier = Modifier
                        .then(if (fillWidth) Modifier.weight(1f) else Modifier)
                        .padding(horizontal = 8.dp),
                )
                trailingContent()
            }
        },
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        shape = shape,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource
    )
}



@Preview
@Composable
private fun ButtonTextPreview() {
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
                    ButtonText(
                        text = "Button Text",
                        onClick = {},
                        containerColor = containerColor,
                        leadingIcon = painterResource(Res.drawable.ic_asterisk),
                        trailingIcon = painterResource(Res.drawable.ic_asterisk),
                    )
                }
            }
        }
    }
}
