package glass.yasan.kepko.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.border.borderStrokeFor
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.resources.painterResource

@Composable
public fun ButtonText(
    text: String?,
    onClick: () -> Unit,
    leadingIcon: Painter?,
    modifier: Modifier = Modifier,
    onClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    onDoubleClick: (() -> Unit)? = null,
    contentModifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.foreground,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = ButtonTextDefaults.shape(),
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Bold,
    border: BorderStroke? = borderStrokeFor(containerColor),
    contentPadding: PaddingValues = ButtonTextDefaults.contentPadding(),
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    annotation: PreferenceAnnotation? = null,
    trailingIcon: Painter? = null,
    hapticFeedbackEnabled: Boolean = true,
    iconTransitionSpecs: ButtonTextIconTransitionSpecs = ButtonTextDefaults.iconTransitionSpecs(),
) {
    ButtonTextInternal(
        text = text,
        modifier = modifier,
        onClick = onClick,
        onClickLabel = onClickLabel,
        onLongClick = onLongClick,
        onLongClickLabel = onLongClickLabel,
        onDoubleClick = onDoubleClick,
        contentModifier = contentModifier,
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        shape = shape,
        textAlign = textAlign,
        fontSize = fontSize,
        fontWeight = fontWeight,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        fillWidth = fillWidth,
        annotation = annotation,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
        leadingContent = {
            AnimatedIcon(
                painter = leadingIcon,
                hasText = text != null,
                alignment = Alignment.CenterStart,
                paddingEdgeIsStart = false,
                transitionSpec = iconTransitionSpecs.leading,
                label = "leadingIcon",
            )
        },
        trailingContent = {
            AnimatedIcon(
                painter = trailingIcon,
                hasText = text != null,
                alignment = Alignment.CenterEnd,
                paddingEdgeIsStart = true,
                transitionSpec = iconTransitionSpecs.trailing,
                label = "trailingIcon",
            )
        },
    )
}

@Composable
private fun AnimatedIcon(
    painter: Painter?,
    hasText: Boolean,
    alignment: Alignment,
    paddingEdgeIsStart: Boolean,
    transitionSpec: AnimatedContentTransitionScope<Painter?>.() -> ContentTransform,
    label: String,
) {
    AnimatedContent(
        targetState = painter,
        transitionSpec = transitionSpec,
        contentAlignment = alignment,
        modifier = Modifier.heightIn(min = KepkoTheme.dimensions.iconSize),
        label = label,
    ) { target ->
        if (target != null) {
            val padding = when {
                !hasText -> Modifier
                paddingEdgeIsStart -> Modifier.padding(start = 12.dp)
                else -> Modifier.padding(end = 12.dp)
            }
            Icon(
                painter = target,
                contentDescription = null,
                modifier = padding,
            )
        }
    }
}

@Composable
public fun ButtonText(
    text: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    onDoubleClick: (() -> Unit)? = null,
    contentModifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.foreground,
    contentColor: Color = contentColorFor(containerColor),
    enabled: Boolean = true,
    shape: Shape = ButtonTextDefaults.shape(),
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Bold,
    border: BorderStroke? = borderStrokeFor(containerColor),
    contentPadding: PaddingValues = ButtonTextDefaults.contentPadding(),
    interactionSource: MutableInteractionSource? = null,
    fillWidth: Boolean = true,
    annotation: PreferenceAnnotation? = null,
    hapticFeedbackEnabled: Boolean = true,
    leadingContent: @Composable RowScope.() -> Unit = {},
    trailingContent: @Composable RowScope.() -> Unit = {},
) {
    ButtonTextInternal(
        text = text,
        modifier = modifier,
        onClick = onClick,
        onClickLabel = onClickLabel,
        onLongClick = onLongClick,
        onLongClickLabel = onLongClickLabel,
        onDoubleClick = onDoubleClick,
        contentModifier = contentModifier,
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = enabled,
        shape = shape,
        textAlign = textAlign,
        fontSize = fontSize,
        fontWeight = fontWeight,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        fillWidth = fillWidth,
        annotation = annotation,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
    )
}

@Suppress("LongParameterList")
@Composable
internal fun ButtonTextInternal(
    text: String?,
    modifier: Modifier,
    onClick: () -> Unit,
    onClickLabel: String?,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    onDoubleClick: (() -> Unit)? = null,
    contentModifier: Modifier,
    containerColor: Color,
    contentColor: Color,
    enabled: Boolean,
    shape: Shape,
    textAlign: TextAlign,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    border: BorderStroke?,
    contentPadding: PaddingValues,
    interactionSource: MutableInteractionSource?,
    fillWidth: Boolean,
    annotation: PreferenceAnnotation?,
    leadingContent: @Composable RowScope.() -> Unit,
    trailingContent: @Composable RowScope.() -> Unit,
    hapticFeedbackEnabled: Boolean,
) {
    ButtonPrimitive(
        onClick = onClick,
        onClickLabel = onClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        onLongClickLabel = onLongClickLabel,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 4.dp,
                    )
                    .heightIn(min = KepkoTheme.dimensions.iconSize)
                    .then(contentModifier),
            ) {
                leadingContent()
                text?.let {
                    Text(
                        text = it.uppercase(),
                        textAlign = textAlign,
                        fontSize = fontSize,
                        fontWeight = fontWeight,
                        maxLines = 1,
                        modifier = Modifier
                            .then(if (fillWidth) Modifier.weight(1f) else Modifier)
                    )
                }
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
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
    )
}


@PreviewWithTest
@Composable
internal fun ButtonTextLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextIconOnlyLightPreview() {
    KepkoTheme(palette = LIGHT) { IconOnlyPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextIconOnlyDarkPreview() {
    KepkoTheme(palette = DARK) { IconOnlyPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextIconOnlyBlackPreview() {
    KepkoTheme(palette = BLACK) { IconOnlyPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextIconOnlySolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { IconOnlyPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ButtonTextIconOnlySolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { IconOnlyPreviewContent() }
}

@Composable
private fun IconOnlyPreviewContent() {
    val containerColors = KepkoTheme.colors.getSemanticColors() +
            KepkoTheme.colors.foreground +
            KepkoTheme.colors.content

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(8.dp),
    ) {
        containerColors.forEach { containerColor ->
            ButtonText(
                text = null,
                onClick = {},
                containerColor = containerColor,
                leadingIcon = painterResource(Res.drawable.ic_asterisk),
                trailingIcon = null,
                fillWidth = false,
            )
        }
    }
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
