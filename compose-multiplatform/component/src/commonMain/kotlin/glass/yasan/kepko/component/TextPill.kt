package glass.yasan.kepko.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.border.borderStrokeFor
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import org.jetbrains.compose.resources.painterResource

@ExperimentalKepkoApi
@Composable
public fun TextPill(
    annotation: PreferenceAnnotation,
    modifier: Modifier = Modifier,
) {
    TextPill(
        text = annotation.text(),
        containerColor = annotation.containerColor(),
        contentColor = annotation.contentColor(),
        leadingIcon = annotation.leadingIcon?.invoke(),
        trailingIcon = annotation.trailingIcon?.invoke(),
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
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Medium,
    textTransformation: (String) -> String = { it.uppercase() },
) {
    val resolvedFontSize = fontSize.takeOrElse { 10.sp }
    val animatedContainerColor by animateColorAsState(containerColor, tween(500))
    val animatedContentColor by animateColorAsState(contentColor, tween(500))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .then(if (border != null) Modifier.border(border, CircleShape) else Modifier)
            .clip(shape = CircleShape)
            .background(color = animatedContainerColor)
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
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextPillDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextPillBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextPillSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextPillSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
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
