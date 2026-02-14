package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import androidx.compose.material3.Checkbox as Material3Checkbox
import androidx.compose.material3.CheckboxDefaults as Material3CheckboxDefaults

@Composable
public fun Checkbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    Material3Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = Material3CheckboxDefaults.colors(
            checkedColor = KepkoTheme.colors.content,
            checkmarkColor = KepkoTheme.colors.foreground,
            disabledCheckedColor = KepkoTheme.colors.contentDisabled,
            disabledIndeterminateColor = KepkoTheme.colors.contentDisabled,
            disabledUncheckedColor = KepkoTheme.colors.contentDisabled,
            uncheckedColor = KepkoTheme.colors.content,
        ),
        modifier = modifier,
    )
}

@PreviewWithTest
@Composable
internal fun CheckboxLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
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
