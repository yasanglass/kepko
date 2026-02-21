package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle

@Composable
public fun PreferenceRadioGroup(
    title: String,
    selectedId: String?,
    items: List<PreferenceRadioGroupItem>,
    onSelectId: (String) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
    annotation: PreferenceAnnotation? = null,
    content: @Composable () -> Unit = {},
) {
    PreferenceRadioGroup(
        title = title,
        selected = items.firstOrNull { it.id == selectedId },
        items = items,
        onSelect = { onSelectId(it.id) },
        modifier = modifier,
        description = description,
        enabled = enabled,
        annotation = annotation,
        content = content,
    )
}

@OptIn(ExperimentalKepkoApi::class)
@Composable
public fun PreferenceRadioGroup(
    title: String,
    selected: PreferenceRadioGroupItem?,
    items: List<PreferenceRadioGroupItem>,
    onSelect: (PreferenceRadioGroupItem) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
    annotation: PreferenceAnnotation? = null,
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
        content = { _: PaddingValues ->
            SegmentedRadioGroupContent(
                items = items,
                selected = selected,
                onSelect = onSelect,
                enabled = enabled,
                content = content,
            )
        }
    )
}

@Composable
private fun SegmentedRadioGroupContent(
    items: List<PreferenceRadioGroupItem>,
    selected: PreferenceRadioGroupItem?,
    onSelect: (PreferenceRadioGroupItem) -> Unit,
    enabled: Boolean,
    content: @Composable () -> Unit,
) {
    SegmentedColumn(items = items) { segmentItems ->
        segmentItems.forEach { radioGroupItem ->
            RadioGroupRow(
                item = radioGroupItem,
                selected = selected == radioGroupItem,
                onClick = { onSelect(radioGroupItem) },
                enabled = enabled && radioGroupItem.enabled,
            )
        }
    }
    content()
}

@Composable
private fun RadioGroupRow(
    item: PreferenceRadioGroupItem,
    selected: Boolean,
    onClick: () -> Unit,
    enabled: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .clickable(
                enabled = enabled,
                onClick = onClick,
            )
            .padding(horizontal = 12.dp),
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
        )
        Text(
            text = item.title(),
            color = if (enabled) KepkoTheme.colors.content else KepkoTheme.colors.contentDisabled,
            modifier = Modifier.weight(1f),
        )
        item.annotation?.let { itemAnnotation ->
            val annotation = if (enabled) itemAnnotation else itemAnnotation.subtle()

            TextPill(
                annotation = annotation,
                modifier = Modifier.padding(horizontal = 12.dp),
            )
        }
    }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val descriptions = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        null
    )

    val items = listOf(
        PreferenceRadioGroupItem("item1") { "Item 1" },
        PreferenceRadioGroupItem("item2", PreferenceAnnotation.experimental) { "Item 2" },
        PreferenceRadioGroupItem("item3", segment = 1) { "Item 3" },
        PreferenceRadioGroupItem("item4", segment = 1, enabled = false) { "Item 4" },
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.midground)
            .padding(vertical = 16.dp),
    ) {
        descriptions.forEach { description ->
            PreferenceRadioGroup(
                title = "PreferenceSlider",
                modifier = Modifier.padding(horizontal = 16.dp),
                description = description,
                annotation = PreferenceAnnotation.beta,
                selected = items.first(),
                items = items,
                onSelect = {},
                content = {
                    Spacer(
                        modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth()
                            .background(KepkoTheme.colors.information),
                    )
                },
            )
            PreferenceRadioGroup(
                title = "PreferenceSlider",
                modifier = Modifier.padding(horizontal = 16.dp),
                description = description,
                selected = items.last(),
                items = items,
                onSelect = {},
                enabled = false,
            )
        }
    }
}
