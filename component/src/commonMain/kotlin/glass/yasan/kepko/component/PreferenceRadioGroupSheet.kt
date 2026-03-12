package glass.yasan.kepko.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.extensions.isFullWidth
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.resource.Icons
import kotlinx.coroutines.launch

@Suppress("LongMethod")
@ExperimentalKepkoApi
@Composable
public fun PreferenceRadioGroupSheet(
    title: String,
    selectedId: String?,
    items: List<PreferenceRadioGroupItem>,
    onSelectItem: (PreferenceRadioGroupItem) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    leadingContent: @Composable () -> Unit = {},
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val contentPadding by animateDpAsState(
            targetValue = if (isFullWidth()) 0.dp else 16.dp,
            label = "contentPadding",
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp),
        ) {
            Row(
                verticalAlignment = if (description == null) Alignment.Top else Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                leadingContent()
                Column {
                    Text(
                        text = title.uppercase(),
                        fontWeight = FontWeight.Bold,
                        color = KepkoTheme.colors.content,
                    )
                    if (description != null) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = description,
                            color = KepkoTheme.colors.contentSubtle,
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            SegmentedColumn(items = items) { segmentItems ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(horizontal = contentPadding),
                ) {
                    segmentItems.forEach { item ->
                        PreferenceRadioButton(
                            title = item.title(),
                            selected = item.id == selectedId,
                            onClick = { onSelectItem(item) },
                            enabled = item.enabled,
                            annotation = item.annotation,
                            leadingContent = {
                                item.icon?.let { painter ->
                                    Icon(
                                        painter = painter,
                                        contentDescription = null,
                                    )
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalKepkoApi
@Composable
public fun PreferenceRadioGroupSheet(
    title: String,
    description: String?,
    selectedId: String?,
    items: List<PreferenceRadioGroupItem>,
    onSelectId: (String) -> Unit,
    visible: Boolean,
    onDismiss: () -> Unit,
    sheetState: SheetState,
    closeOnSelection: Boolean,
    leadingContent: @Composable () -> Unit = {},
) {
    if (visible) {
        val scope = rememberCoroutineScope()

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            content = {
                PreferenceRadioGroupSheet(
                    title = title,
                    description = description,
                    selectedId = selectedId,
                    items = items,
                    onSelectItem = { item ->
                        onSelectId(item.id)
                        if (closeOnSelection) {
                            scope
                                .launch { sheetState.hide() }
                                .invokeOnCompletion { onDismiss() }
                        }
                    },
                    leadingContent = leadingContent,
                )
            },
        )
    }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupSheetContentLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { SheetContentPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupSheetContentDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { SheetContentPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupSheetContentBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { SheetContentPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupSheetContentSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { SheetContentPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupSheetContentSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { SheetContentPreviewContent() }
}

@Composable
private fun SheetContentPreviewContent() {
    val items = listOf(
        PreferenceRadioGroupItem("item1", icon = Icons.check) { "Item 1" },
        PreferenceRadioGroupItem("item2", PreferenceAnnotation.experimental) { "Item 2" },
    )

    PreferenceRadioGroupSheet(
        title = "Preference",
        description = "Lorem ipsum dolor sit amet.",
        selectedId = "item1",
        items = items,
        onSelectItem = {},
        leadingContent = {
            Icon(
                painter = Icons.settings,
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp),
            )
        },
    )
}
