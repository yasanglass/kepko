package glass.yasan.concrete.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.concrete.foundation.theme.ConcreteTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
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
            checkedBorderColor = ConcreteTheme.colors.content,
            checkedThumbColor = ConcreteTheme.colors.foreground,
            checkedTrackColor = ConcreteTheme.colors.content,
            disabledCheckedBorderColor = ConcreteTheme.colors.contentDisabled,
            disabledCheckedThumbColor = ConcreteTheme.colors.contentDisabled,
            disabledCheckedTrackColor = ConcreteTheme.colors.midground,
            disabledUncheckedBorderColor = ConcreteTheme.colors.contentDisabled,
            disabledUncheckedThumbColor = ConcreteTheme.colors.contentDisabled,
            disabledUncheckedTrackColor = ConcreteTheme.colors.midground,
            uncheckedBorderColor = ConcreteTheme.colors.content,
            uncheckedThumbColor = ConcreteTheme.colors.content,
            uncheckedTrackColor = ConcreteTheme.colors.foreground,
        ),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun SwitchPreview() {
    val checked = arrayOf(true, false)
    val enabled = arrayOf(true, false)

    Column {
        ConcreteTheme {
            Column(
                modifier = Modifier.background(ConcreteTheme.colors.foreground)
            ) {
                checked.forEach { isChecked ->
                    enabled.forEach { isEnabled ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp),
                        ) {
                            val checkedStatus = if (isChecked) "Checked" else "Unchecked"
                            val enabledStatus = if (isEnabled) "Enabled" else "Disabled"

                            Text(
                                text = "Switch: $checkedStatus + $enabledStatus",
                                modifier = Modifier.weight(1f),
                            )
                            Switch(
                                checked = isChecked,
                                onCheckedChange = {},
                                enabled = isEnabled,
                                modifier = Modifier.padding(8.dp),
                            )
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
