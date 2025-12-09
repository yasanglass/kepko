package glass.yasan.concrete.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
            checkedColor = ConcreteTheme.colors.content,
            checkmarkColor = ConcreteTheme.colors.foreground,
            disabledCheckedColor = ConcreteTheme.colors.contentDisabled,
            disabledIndeterminateColor = ConcreteTheme.colors.contentDisabled,
            disabledUncheckedColor = ConcreteTheme.colors.contentDisabled,
            uncheckedColor = ConcreteTheme.colors.content,
        ),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun CheckboxPreview() {
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
                                text = "Checkbox: $checkedStatus + $enabledStatus",
                                modifier = Modifier.weight(1f),
                            )
                            Checkbox(
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
