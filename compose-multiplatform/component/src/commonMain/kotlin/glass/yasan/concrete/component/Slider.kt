package glass.yasan.concrete.component

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.concrete.foundation.theme.ConcreteTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Slider as Material3Slider
import androidx.compose.material3.SliderDefaults as Material3SliderDefaults

@Composable
public fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (() -> Unit)? = null,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Material3Slider(
        value = value,
        onValueChange = onValueChange,
        onValueChangeFinished = onValueChangeFinished,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        interactionSource = interactionSource,
        colors = Material3SliderDefaults.colors(
            activeTickColor = ConcreteTheme.colors.foreground,
            activeTrackColor = ConcreteTheme.colors.content,
            disabledActiveTickColor = ConcreteTheme.colors.foreground,
            disabledActiveTrackColor = ConcreteTheme.colors.contentSubtle,
            disabledInactiveTickColor = ConcreteTheme.colors.contentSubtle,
            disabledInactiveTrackColor = ConcreteTheme.colors.background,
            disabledThumbColor = ConcreteTheme.colors.background,
            inactiveTickColor = ConcreteTheme.colors.content,
            inactiveTrackColor = ConcreteTheme.colors.background,
            thumbColor = ConcreteTheme.colors.content,
        ),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun SliderPreview() {
    val values = arrayOf(0, 25, 50, 75, 100)
    val enabled = arrayOf(true, false)

    Column {
        ConcreteTheme {
            Column(
                modifier = Modifier.background(ConcreteTheme.colors.foreground)
            ) {
                values.forEach { value ->
                    enabled.forEach { isEnabled ->
                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Slider(
                                value = value / 100f,
                                onValueChange = {},
                                steps = 5,
                                enabled = isEnabled,
                            )
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
