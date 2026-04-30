package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.border.borderStroke
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.resources.painterResource

@Composable
public fun SelectableChip(
    title: String,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape(),
    contentPadding: PaddingValues = ButtonDefaults.contentPadding(),
    fillWidth: Boolean = false,
    colors: SelectableChipColors = SelectableChipDefaults.colors(),
) {
    Button(
        text = title,
        onClick = { onSelectedChange(!selected) },
        leadingIcon = leadingIcon,
        modifier = modifier,
        containerColor = if (selected) colors.selectedContainerColor else colors.unselectedContainerColor,
        contentColor = if (selected) colors.selectedContentColor else colors.unselectedContentColor,
        enabled = enabled,
        shape = shape,
        border = borderStroke(color = colors.outlineColor),
        contentPadding = contentPadding,
        fillWidth = fillWidth,
    )
}

@PreviewWithTest
@Composable
internal fun SelectableChipLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SelectableChipDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SelectableChipBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SelectableChipSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SelectableChipSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val states = listOf(
        true to true,
        false to true,
        true to false,
        false to false,
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.midground)
            .padding(vertical = 16.dp),
    ) {
        states.forEach { (selected, enabled) ->
            SelectableChip(
                title = "SelectableChip",
                selected = selected,
                enabled = enabled,
                onSelectedChange = {},
                leadingIcon = painterResource(Res.drawable.ic_asterisk),
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}
