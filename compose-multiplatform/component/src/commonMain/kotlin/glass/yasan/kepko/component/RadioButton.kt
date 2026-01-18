package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.color.ProvideLocalMinimumInteractiveComponentSize
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import androidx.compose.material3.RadioButton as Material3RadioButton
import androidx.compose.material3.RadioButtonDefaults as Material3RadioButtonDefaults

@Composable
public fun RadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    minimumInteractiveComponentSize: Dp? = null,
) {
    ProvideLocalMinimumInteractiveComponentSize(size = minimumInteractiveComponentSize) {
        Material3RadioButton(
            selected = selected,
            enabled = enabled,
            colors = Material3RadioButtonDefaults.colors(
                disabledSelectedColor = KepkoTheme.colors.contentDisabled,
                disabledUnselectedColor = KepkoTheme.colors.contentDisabled,
                selectedColor = KepkoTheme.colors.content,
                unselectedColor = KepkoTheme.colors.content,
            ),
            interactionSource = interactionSource,
            onClick = onClick,
            modifier = modifier,
        )
    }
}

@PreviewWithTest
@Composable
internal fun RadioButtonLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun RadioButtonDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun RadioButtonBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun RadioButtonSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun RadioButtonSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val selected = arrayOf(true, false)
    val enabled = arrayOf(true, false)

    Column(
        modifier = Modifier.background(KepkoTheme.colors.foreground)
    ) {
        enabled.forEach { isEnabled ->
            selected.forEach { isSelected ->
                RadioButton(
                    selected = isSelected,
                    enabled = isEnabled,
                    onClick = {},
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}
