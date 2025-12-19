package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@ExperimentalKepkoApi
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
        content = {
            Column {
                items.forEach { radioGroupItem ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(KepkoTheme.shapes.button)
                            .clickable(
                                enabled = enabled,
                                onClick = { onSelect(radioGroupItem) },
                            ),
                    ) {
                        RadioButton(
                            selected = selected == radioGroupItem,
                            onClick = { onSelect(radioGroupItem) },
                            enabled = enabled,
                        )
                        Text(
                            text = radioGroupItem.title(),
                            modifier = Modifier.weight(1f),
                        )
                        radioGroupItem.annotation?.let { itemAnnotation ->
                            TextPill(
                                text = itemAnnotation.text(),
                                containerColor = itemAnnotation.containerColor(),
                                contentColor = itemAnnotation.contentColor(),
                                modifier = Modifier.padding(horizontal = 12.dp),
                            )
                        }
                    }
                }
                content()
            }
        }
    )
}

public data class PreferenceRadioGroupItem(
    val id: String,
    val annotation: PreferenceAnnotation? = null,
    val title: @Composable () -> String
)

@Preview
@Composable
private fun PreferenceRadioGroupPreview() {
    val annotations = arrayOf(PreferenceAnnotation.new, null)
    val descriptions = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        null
    )

    val items = listOf(
        PreferenceRadioGroupItem("item1") { "Item 1" },
        PreferenceRadioGroupItem("item2", PreferenceAnnotation.experimental) { "Item 2" },
        PreferenceRadioGroupItem("item3") { "Item 3" },
    )

    KepkoTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .background(KepkoTheme.colors.midground)
                .padding(vertical = 16.dp),
        ) {
            annotations.forEach { annotation ->
                descriptions.forEach { description ->

                    PreferenceRadioGroup(
                        title = "Preference Slider",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        description = description,
                        annotation = annotation,
                        selected = items.first(),
                        items = items,
                        onSelect = {},
                        content = {
                            Spacer(
                                modifier = Modifier
                                    .height(32.dp)
                                    .fillMaxWidth()
                                    .background(KepkoTheme.colors.caution),
                            )
                        },
                    )
                }
            }
        }
    }
}
