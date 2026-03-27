package glass.yasan.kepko.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.AnimationSpec
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Fonts

/**
 * A text component that renders each character in an equal-width slot,
 * with built-in animations for character changes.
 *
 * @param charWidth the width of each character slot. If not specified,
 * it is automatically calculated.
 * @param animationSpec [AnimationSpec] used for the character change animations.
 * Pass `null` to disable animations.
 */
@ExperimentalKepkoApi
@Composable
public fun TextTabular(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    charWidth: Dp? = null,
    animationSpec: AnimationSpec<Float>? = TextTabularDefaults.animationSpec(),
) {
    val textMeasurer = rememberTextMeasurer()
    val style = TextStyle(
        fontFamily = fontFamily ?: Fonts.rubikFontFamily(),
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        color = color,
    )
    val density = LocalDensity.current
    val resolvedCharWidth: Dp = charWidth ?: remember(style, density) {
        val widest = TextTabularDefaults.defaultCandidates.maxOf {
            textMeasurer.measure(it.toString(), style).size.width
        }
        with(density) { widest.toDp() }
    }

    Row(modifier = modifier) {
        text.forEach { char ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.width(resolvedCharWidth),
            ) {
                if (animationSpec != null) {
                    AnimatedCharSlot(
                        targetChar = char,
                        style = style,
                        animationSpec = animationSpec,
                    )
                } else {
                    BasicText(
                        text = char.toString(),
                        style = style,
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimatedCharSlot(
    targetChar: Char,
    style: TextStyle,
    animationSpec: AnimationSpec<Float>,
) {
    var displayChar by remember { mutableStateOf(targetChar) }
    var previousChar by remember { mutableStateOf(targetChar) }
    var charHeight by remember { mutableFloatStateOf(0f) }
    var direction by remember { mutableIntStateOf(1) }
    val progress = remember { Animatable(1f) }

    LaunchedEffect(targetChar) {
        if (displayChar != targetChar) {
            previousChar = displayChar
            direction = if (previousChar.isDigit() && targetChar.isDigit()) {
                val diff = targetChar - previousChar
                val goingUp = if (diff > 5) false else if (diff < -5) true else diff > 0
                if (goingUp) -1 else 1
            } else {
                -1
            }
            displayChar = targetChar
            progress.snapTo(0f)
            progress.animateTo(
                targetValue = 1f,
                animationSpec = animationSpec,
            )
        }
    }

    val p = progress.value

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clipToBounds(),
    ) {
        if (p < 1f) {
            BasicText(
                text = previousChar.toString(),
                style = style,
                modifier = Modifier.graphicsLayer {
                    translationY = direction * -charHeight * p
                    alpha = (1f - p * 2.5f).coerceIn(0f, 1f)
                },
            )
        }
        BasicText(
            text = displayChar.toString(),
            style = style,
            onTextLayout = { charHeight = it.size.height.toFloat() },
            modifier = Modifier.graphicsLayer {
                translationY = direction * charHeight * (1f - p)
                alpha = (p * 2.5f - 0.5f).coerceIn(0f, 1f)
            },
        )
    }
}

@PreviewWithTest
@Composable
internal fun TextTabularLightPreview() {
    KepkoTheme(palette = LIGHT) { TextTabularPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextTabularDarkPreview() {
    KepkoTheme(palette = DARK) { TextTabularPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextTabularBlackPreview() {
    KepkoTheme(palette = BLACK) { TextTabularPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextTabularSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { TextTabularPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextTabularSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { TextTabularPreviewContent() }
}

@Composable
private fun TextTabularPreviewContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(2.dp),
    ) {
        val samples = arrayOf(
            "012345",
            "678999",
            "1.0000",
            "99:999",
            "-3.141",
            "+4.449",
            "0.5000",
            "100.00",
        )
        samples.forEach { sample ->
            TextTabular(
                text = sample,
                color = KepkoTheme.colors.onInformation,
                modifier = Modifier.background(KepkoTheme.colors.information),
            )
        }
    }
}
