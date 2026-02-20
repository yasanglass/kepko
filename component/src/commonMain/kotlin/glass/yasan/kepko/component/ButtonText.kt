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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.border.borderStrokeFor
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import org.jetbrains.compose.resources.painterResource

@Composable
public fun ButtonText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.foreground,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Bold,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonTextDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonTextDefaults.contentPadding(),
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    annotation: PreferenceAnnotation? = null,
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
        fontSize = fontSize,
        fontWeight = fontWeight,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        fillWidth = fillWidth,
        annotation = annotation,
        leadingContent = {
            leadingIcon?.let { painter ->
                Icon(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 12.dp),
                )
            }
        },
        trailingContent = {
            trailingIcon?.let { painter ->
                Icon(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 12.dp),
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
    containerColor: Color = KepkoTheme.colors.foreground,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Bold,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonTextDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonTextDefaults.contentPadding(),
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    annotation: PreferenceAnnotation? = null,
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
        fontSize = fontSize,
        fontWeight = fontWeight,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        fillWidth = fillWidth,
        annotation = annotation,
        leadingContent = {
            leadingIcon?.let { imageVector ->
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 12.dp),
                )
            }
        },
        trailingContent = {
            trailingIcon?.let { imageVector ->
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 12.dp),
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
    containerColor: Color = KepkoTheme.colors.foreground,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Bold,
    border: BorderStroke? = borderStrokeFor(containerColor),
    elevation: ButtonElevation? = ButtonTextDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonTextDefaults.contentPadding(),
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    annotation: PreferenceAnnotation? = null,
    leadingContent: @Composable RowScope.() -> Unit = {},
    trailingContent: @Composable RowScope.() -> Unit = {},
) {
    Button(
        onClick = onClick,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 4.dp,
                    )
                    .then(contentModifier),
            ) {
                leadingContent()
                Text(
                    text = text.uppercase(),
                    textAlign = textAlign,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    maxLines = 1,
                    modifier = Modifier
                        .then(if (fillWidth) Modifier.weight(1f) else Modifier)
                )
                annotation?.let {
                    TextPill(
                        annotation = it,
                        modifier = Modifier.padding(start = 12.dp),
                    )
                }
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


@PreviewWithTest
@Composable
internal fun ButtonTextLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val containerColors = KepkoTheme.colors.getSemanticColors() +
            KepkoTheme.colors.foreground +
            KepkoTheme.colors.content

    Column(
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(vertical = 4.dp),
    ) {
        containerColors.forEach { containerColor ->
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(
                        vertical = 2.dp,
                        horizontal = 8.dp,
                    ),
            ) {
                ButtonText(
                    text = "Button Text",
                    onClick = {},
                    containerColor = containerColor,
                    leadingIcon = painterResource(Res.drawable.ic_asterisk),
                    trailingIcon = painterResource(Res.drawable.ic_asterisk),
                    annotation = PreferenceAnnotation.preview,
                )
                ButtonText(
                    text = "Button Text",
                    onClick = {},
                    containerColor = containerColor,
                )
            }
        }
    }
}
