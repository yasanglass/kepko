package glass.yasan.kepko.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import androidx.compose.material3.Checkbox as Material3Checkbox
import androidx.compose.material3.CheckboxColors as Material3CheckboxColors

@Composable
public fun Checkbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    val colorAnimationSpec = spring<Color>(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessLow,
    )
    val checkedCheckmarkColor by animateColorAsState(colors.checkedCheckmarkColor, colorAnimationSpec)
    val uncheckedCheckmarkColor by animateColorAsState(colors.uncheckedCheckmarkColor, colorAnimationSpec)
    val checkedBoxColor by animateColorAsState(colors.checkedBoxColor, colorAnimationSpec)
    val uncheckedBoxColor by animateColorAsState(colors.uncheckedBoxColor, colorAnimationSpec)
    val checkedBorderColor by animateColorAsState(colors.checkedBorderColor, colorAnimationSpec)
    val uncheckedBorderColor by animateColorAsState(colors.uncheckedBorderColor, colorAnimationSpec)

    Material3Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = Material3CheckboxColors(
            checkedCheckmarkColor = checkedCheckmarkColor,
            uncheckedCheckmarkColor = uncheckedCheckmarkColor,
            checkedBoxColor = checkedBoxColor,
            uncheckedBoxColor = uncheckedBoxColor,
            disabledCheckedBoxColor = colors.disabledCheckedBoxColor,
            disabledUncheckedBoxColor = colors.disabledUncheckedBoxColor,
            disabledIndeterminateBoxColor = colors.disabledIndeterminateBoxColor,
            checkedBorderColor = checkedBorderColor,
            uncheckedBorderColor = uncheckedBorderColor,
            disabledBorderColor = colors.disabledBorderColor,
            disabledUncheckedBorderColor = colors.disabledUncheckedBorderColor,
            disabledIndeterminateBorderColor = colors.disabledIndeterminateBorderColor,
        ),
        modifier = modifier,
    )
}

@PreviewWithTest
@Composable
internal fun CheckboxLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val checked = arrayOf(true, false)
    val enabled = arrayOf(true, false)

    Column(
        modifier = Modifier.background(KepkoTheme.colors.foreground)
    ) {
        checked.forEach { isChecked ->
            enabled.forEach { isEnabled ->
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {},
                    enabled = isEnabled,
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}
