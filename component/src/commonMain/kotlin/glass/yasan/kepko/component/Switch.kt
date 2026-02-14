package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import androidx.compose.material3.Switch as Material3Switch
import androidx.compose.material3.SwitchDefaults as Material3SwitchDefaults

@Composable
public fun Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Material3Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        colors = Material3SwitchDefaults.colors(
            // Checked
            checkedBorderColor = KepkoTheme.colors.content,
            checkedThumbColor = KepkoTheme.colors.foreground,
            checkedTrackColor = KepkoTheme.colors.content,
            // Unchecked
            uncheckedBorderColor = KepkoTheme.colors.content,
            uncheckedThumbColor = KepkoTheme.colors.content,
            uncheckedTrackColor = KepkoTheme.colors.foreground,
            // Checked Disabled
            disabledCheckedBorderColor = KepkoTheme.colors.contentDisabled,
            disabledCheckedThumbColor = KepkoTheme.colors.foreground,
            disabledCheckedTrackColor = KepkoTheme.colors.contentDisabled,
            // Unchecked Disabled
            disabledUncheckedBorderColor = KepkoTheme.colors.contentDisabled,
            disabledUncheckedThumbColor = KepkoTheme.colors.contentDisabled,
            disabledUncheckedTrackColor = KepkoTheme.colors.foreground,
        ),
        modifier = modifier,
    )
}

@PreviewWithTest
@Composable
internal fun SwitchLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SwitchDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SwitchBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SwitchSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SwitchSolarizedDarkPreview() {
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
                Switch(
                    checked = isChecked,
                    onCheckedChange = {},
                    enabled = isEnabled,
                    modifier = Modifier
                        .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp,
                        ),
                )
            }
        }
    }
}
