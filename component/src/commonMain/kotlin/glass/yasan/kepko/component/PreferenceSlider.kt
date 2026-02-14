package glass.yasan.kepko.component

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle

@OptIn(ExperimentalKepkoApi::class)
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
                Slider(
                    value = value,
                    onValueChange = onValueChange,
                    onValueChangeFinished = onValueChangeFinished,
                    valueRange = valueRange,
                    steps = steps,
                    enabled = enabled,
                )
                content()
            }
        }
    )
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSliderSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val annotations = arrayOf(PreferenceAnnotation.new, null)
    val descriptions = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        null
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.midground)
            .padding(vertical = 16.dp),
    ) {
        annotations.forEach { annotation ->
            descriptions.forEach { description ->
                PreferenceSlider(
                    title = "PreferenceSlider",
                    description = description,
                    value = 0.5f,
                    steps = 5,
                    onValueChange = {},
                    annotation = annotation,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
        }
    }
}
