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
import org.jetbrains.compose.ui.tooling.preview.Preview

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

@OptIn(ExperimentalKepkoApi::class)
@Preview
@Composable
private fun PreferenceSliderPreview() {
    val annotations = arrayOf(PreferenceAnnotation.new, null)
    val descriptions = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        null
    )

    KepkoTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .background(KepkoTheme.colors.midground)
                .padding(vertical = 16.dp),
        ) {
            annotations.forEach { annotation ->
                descriptions.forEach { description ->
                    PreferenceSlider(
                        title = "Preference Slider",
                        description = description,
                        value = 0.5f,
                        onValueChange = {},
                        annotation = annotation,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }
            }
        }
    }
}
