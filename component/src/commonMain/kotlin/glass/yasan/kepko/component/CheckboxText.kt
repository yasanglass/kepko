package glass.yasan.kepko.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.theme.KepkoTheme

@Composable
public fun CheckboxText(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = LocalIndication.current,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .clip(KepkoTheme.shapes.extraLarge)
            .clickable(
                interactionSource = interactionSource,
                indication = indication,
                enabled = enabled,
                onClick = { onCheckedChange(!checked) },
            )
            .padding(horizontal = 8.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (enabled) KepkoTheme.colors.content else KepkoTheme.colors.contentDisabled,
            modifier = Modifier.weight(1f),
        )
    }
}

@PreviewWithTest
@Composable
internal fun CheckboxTextLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxTextDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxTextBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxTextSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun CheckboxTextSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val checked = arrayOf(true, false)
    val enabled = arrayOf(true, false)

    Column(
        modifier = Modifier.background(KepkoTheme.colors.foreground),
    ) {
        checked.forEach { isChecked ->
            enabled.forEach { isEnabled ->
                CheckboxText(
                    text = "CheckboxText",
                    checked = isChecked,
                    onCheckedChange = {},
                    enabled = isEnabled,
                )
            }
        }
    }
}
