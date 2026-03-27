package glass.yasan.kepko.component

import androidx.annotation.IntRange
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import kotlin.math.pow
import kotlin.math.roundToLong

@Composable
public fun PreferenceSlider(
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
    annotation: PreferenceAnnotation? = null,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    transformValueLabel: ((Float) -> String?)? = null,
    content: @Composable () -> Unit = {},
) {
    val decimalPlaces = decimalPlacesForSteps(valueRange, steps)
    val effectiveTransformation = transformValueLabel ?: { v ->
        formatFixed(v, decimalPlaces)
    }
    val label = effectiveTransformation(value)
    val contentColor = if (enabled) {
        KepkoTheme.colors.content
    } else {
        KepkoTheme.colors.contentDisabled
    }

    PreferenceSlider(
        title = title,
        value = value,
        onValueChange = onValueChange,
        onValueChangeFinished = onValueChangeFinished,
        modifier = modifier,
        description = description,
        enabled = enabled,
        annotation = annotation,
        valueRange = valueRange,
        steps = steps,
        valueLabel = {
            AnimatedVisibility(
                visible = label != null,
                enter = expandHorizontally() + fadeIn(),
                exit = shrinkHorizontally() + fadeOut(),
            ) {
                label?.let {
                    TextTabular(
                        text = it,
                        color = contentColor,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                }
            }
        },
        content = content,
    )
}

@Composable
public fun PreferenceSlider(
    title: String,
    value: Float,
    valueLabel: (@Composable () -> Unit)?,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
    annotation: PreferenceAnnotation? = null,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    content: @Composable () -> Unit = {},
) {
    PreferenceContainer(
        title = title,
        description = description,
        enabled = enabled,
        annotation = annotation,
        modifier = modifier,
        interactionSource = null,
        indication = null,
        content = { contentPadding: PaddingValues ->
            Column(
                modifier = Modifier.padding(contentPadding),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Slider(
                        value = value,
                        onValueChange = onValueChange,
                        onValueChangeFinished = onValueChangeFinished,
                        valueRange = valueRange,
                        steps = steps,
                        enabled = enabled,
                        modifier = Modifier.weight(1f),
                    )
                    valueLabel?.invoke()
                }
                content()
            }
        }
    )
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.midground)
            .padding(vertical = 16.dp),
    ) {
        PreferenceSlider(
            title = "Float label",
            description = "With default float formatting.",
            value = 0.5f,
            steps = 1,
            onValueChange = {},
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        PreferenceSlider(
            title = "Integer label",
            description = "Range 0..10 with integer steps.",
            value = 7f,
            valueRange = 0f..10f,
            steps = 9,
            onValueChange = {},
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        PreferenceSlider(
            title = "No label",
            description = "Label hidden via null transformation.",
            value = 0.5f,
            steps = 5,
            onValueChange = {},
            transformValueLabel = { null },
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        PreferenceSlider(
            title = "With annotation",
            value = 0.5f,
            steps = 5,
            onValueChange = {},
            annotation = PreferenceAnnotation.earlyAccess,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}

private fun decimalPlacesForSteps(
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
): Int {
    if (steps <= 0) return 0

    val stepSize = (valueRange.endInclusive - valueRange.start) / (steps + 1)
    for (places in 0..6) {
        val factor = 10f.pow(places)
        val scaled = stepSize * factor
        if ((scaled - scaled.roundToLong()).let { if (it < 0) -it else it } < 0.001f) return places
    }

    return 2
}

private fun formatFixed(value: Float, decimalPlaces: Int): String {
    if (decimalPlaces <= 0) return value.roundToLong().toString()

    val factor = 10f.pow(decimalPlaces).toLong()
    val rounded = (value * factor).roundToLong()
    val negative = rounded < 0
    val abs = if (negative) -rounded else rounded
    val intPart = abs / factor
    val fracPart = abs % factor
    val prefix = if (negative) "-" else ""

    return "$prefix$intPart.${fracPart.toString().padStart(decimalPlaces, '0')}"
}
