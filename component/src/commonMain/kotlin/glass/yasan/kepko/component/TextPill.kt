package glass.yasan.kepko.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Shape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.takeOrElse
import glass.yasan.kepko.foundation.border.borderStrokeFor
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.resources.painterResource

@Composable
public fun TextPill(
    badge: Badge,
    modifier: Modifier = Modifier,
    shape: Shape = TextPillDefaults.shape,
    animations: TextPillDefaults.Animations = TextPillDefaults.animations(),
    onClick: (() -> Unit)? = null,
    onClickInteractionSource: MutableInteractionSource? = null,
    onClickIndication: Indication? = null,
) {
    TextPill(
        text = badge.text(),
        containerColor = badge.containerColor(),
        contentColor = badge.contentColor(),
        leadingIcon = badge.leadingIcon?.invoke(),
        trailingIcon = badge.trailingIcon?.invoke(),
        onClick = onClick,
        onClickInteractionSource = onClickInteractionSource,
        onClickIndication = onClickIndication,
        shape = shape,
        animations = animations,
        modifier = modifier,
    )
}

@Composable
public fun TextPill(
    text: String,
    containerColor: Color,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    contentColor: Color = contentColorFor(containerColor),
    border: BorderStroke? = borderStrokeFor(containerColor),
    shape: Shape = TextPillDefaults.shape,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Medium,
    textTransformation: (String) -> String = { it.uppercase() },
    animations: TextPillDefaults.Animations = TextPillDefaults.animations(),
    onClick: (() -> Unit)? = null,
    onClickInteractionSource: MutableInteractionSource? = null,
    onClickIndication: Indication? = null,
) {
    val resolvedFontSize = fontSize.takeOrElse { 10.sp }
    val animatedContainerColor by animateColorAsState(containerColor, animations.colorAnimationSpec)
    val animatedContentColor by animateColorAsState(contentColor, animations.colorAnimationSpec)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .clip(shape = shape)
            .background(color = animatedContainerColor)
            .animateContentSize(animationSpec = animations.sizeAnimationSpec)
            .then(
                if (onClick != null) {
                    if (onClickInteractionSource != null || onClickIndication != null) {
                        Modifier.clickable(
                            interactionSource = onClickInteractionSource,
                            indication = onClickIndication,
                            onClick = onClick,
                        )
                    } else {
                        Modifier.clickable(onClick = onClick)
                    }
                } else {
                    Modifier
                },
            )
            .padding(horizontal = 12.dp)
    ) {
        leadingIcon?.let {
            Image(
                painter = it,
                contentDescription = null,
                colorFilter = ColorFilter.tint(animatedContentColor),
                modifier = Modifier.size(resolvedFontSize.value.dp),
            )
        }
        Text(
            text = textTransformation(text),
            fontSize = resolvedFontSize,
            color = animatedContentColor,
            fontWeight = fontWeight,
            maxLines = 1,
        )
        trailingIcon?.let {
            Image(
                painter = it,
                contentDescription = null,
                colorFilter = ColorFilter.tint(animatedContentColor),
                modifier = Modifier.size(resolvedFontSize.value.dp),
            )
        }
    }
}

@PreviewWithTest
@Composable
internal fun TextPillLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextPillDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextPillBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextPillSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextPillSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val containerColors = KepkoTheme.colors.getSemanticColors() +
            KepkoTheme.colors.foreground +
            KepkoTheme.colors.content

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(4.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            TextPill(badge = Badge(text = { "TextPill" }))
        }
        containerColors.forEach { containerColor ->
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                TextPill(
                    text = "TextPill",
                    containerColor = containerColor,
                )
                TextPill(
                    text = "TextPill",
                    containerColor = containerColor,
                    leadingIcon = painterResource(Res.drawable.ic_asterisk),
                )
                TextPill(
                    text = "TextPill",
                    containerColor = containerColor,
                    trailingIcon = painterResource(Res.drawable.ic_asterisk),
                )
                TextPill(
                    text = "TextPill",
                    containerColor = containerColor,
                    leadingIcon = painterResource(Res.drawable.ic_asterisk),
                    trailingIcon = painterResource(Res.drawable.ic_asterisk),
                )
            }
        }
    }
}
