package glass.yasan.kepko.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.border.borderStroke
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalKepkoApi
@Composable
public fun PreferenceRadioGroupPickerChip(
    title: String,
    selectedId: String?,
    items: List<PreferenceRadioGroupItem>,
    onSelectId: (String) -> Unit,
    leadingIcon: Painter,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    description: String? = null,
    enabled: Boolean = true,
    closeOnSelection: Boolean = true,
    displayMode: PreferenceRadioGroupPickerChipDisplayMode = ICON_WITH_TEXT,
    colors: PreferenceRadioGroupPickerChipColors = PreferenceRadioGroupPickerChipDefaults.colors(),
) {
    PreferenceRadioGroupPickerChip(
        title = title,
        selectedId = selectedId,
        items = items,
        onSelectId = onSelectId,
        modifier = modifier,
        sheetState = sheetState,
        description = description,
        enabled = enabled,
        closeOnSelection = closeOnSelection,
        displayMode = displayMode,
        colors = colors,
        leadingContent = {
            Icon(
                painter = leadingIcon,
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp),
            )
        },
    )
}

@Suppress("LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalKepkoApi
@Composable
public fun PreferenceRadioGroupPickerChip(
    title: String,
    selectedId: String?,
    items: List<PreferenceRadioGroupItem>,
    onSelectId: (String) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    description: String? = null,
    enabled: Boolean = true,
    closeOnSelection: Boolean = true,
    displayMode: PreferenceRadioGroupPickerChipDisplayMode = ICON_WITH_TEXT,
    colors: PreferenceRadioGroupPickerChipColors = PreferenceRadioGroupPickerChipDefaults.colors(),
    leadingContent: @Composable () -> Unit = {},
) {
    var showSheet by remember { mutableStateOf(false) }
    val selectedItem = items.firstOrNull { it.id == selectedId }
    var showTitle by remember { mutableStateOf(false) }
    var isInitialComposition by remember { mutableStateOf(true) }
    val hiddenSelectedTitle = when {
        selectedItem?.icon == null -> null
        displayMode == ICON_WITH_TEXT -> null
        showTitle -> null
        else -> selectedItem.title()
    }

    LaunchedEffect(selectedId) {
        if (isInitialComposition) {
            isInitialComposition = false
            return@LaunchedEffect
        }
        showTitle = displayMode == ICON_WITH_TEXT_REVEAL && selectedItem?.icon != null
    }

    LaunchedEffect(showTitle, showSheet) {
        if (!showTitle || showSheet) return@LaunchedEffect
        delay(1.3.seconds)
        showTitle = false
    }

    ButtonPrimitive(
        onClick = { showSheet = true },
        enabled = enabled,
        containerColor = colors.containerColor,
        contentColor = colors.contentColor,
        border = borderStroke(color = colors.outlineColor),
        shape = ButtonDefaults.shape(),
        contentPadding = ButtonDefaults.contentPadding(),
        modifier = modifier.then(
            if (hiddenSelectedTitle != null) {
                Modifier.semantics { contentDescription = hiddenSelectedTitle }
            } else {
                Modifier
            }
        ),
        content = {
            AnimatedContent(
                targetState = selectedItem to showTitle,
                transitionSpec = { fadeIn() togetherWith fadeOut() using SizeTransform(clip = false) },
                contentKey = { (item, title) -> item?.id to title },
                label = "selectedItem",
            ) { (item, titleVisible) ->
                val shouldShowTitle = when {
                    item == null -> false
                    item.icon == null -> true
                    displayMode == ICON_WITH_TEXT -> true
                    else -> titleVisible
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 4.dp)
                        .defaultMinSize(minHeight = KepkoTheme.dimensions.iconSize),
                ) {
                    if (item != null) {
                        item.icon?.let { painter ->
                            Icon(painter = painter)
                        }
                        if (shouldShowTitle) {
                            Text(
                                text = item.title().uppercase(),
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                modifier = if (item.icon != null) Modifier.padding(start = 12.dp) else Modifier,
                            )
                            item.annotation?.let {
                                TextPill(
                                    annotation = it,
                                    modifier = Modifier.padding(start = 12.dp),
                                )
                            }
                        }
                    } else {
                        leadingContent()
                        Text(
                            text = title.uppercase(),
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                        )
                    }
                }
            }
        },
    )

    PreferenceRadioGroupSheet(
        title = title,
        description = description,
        selectedId = selectedId,
        items = items,
        onSelectId = onSelectId,
        visible = showSheet,
        onDismiss = { showSheet = false },
        sheetState = sheetState,
        closeOnSelection = closeOnSelection,
        leadingContent = leadingContent,
    )
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupPickerChipLightPreview() {
    KepkoTheme(palette = LIGHT) { CompactPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupPickerChipDarkPreview() {
    KepkoTheme(palette = DARK) { CompactPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupPickerChipBlackPreview() {
    KepkoTheme(palette = BLACK) { CompactPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupPickerChipSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { CompactPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupPickerChipSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { CompactPreviewContent() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CompactPreviewContent() {
    val items = listOf(
        PreferenceRadioGroupItem("item1", icon = Icons.check) { "Item 1" },
        PreferenceRadioGroupItem("item2", PreferenceAnnotation.experimental) { "Item 2" },
        PreferenceRadioGroupItem("item3", segment = 1, icon = Icons.info) { "Item 3" },
        PreferenceRadioGroupItem("item4", segment = 1, enabled = false) { "Item 4" },
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.midground)
            .padding(vertical = 16.dp),
    ) {
        PreferenceRadioGroupPickerChip(
            title = "Preference",
            selectedId = "item1",
            items = items,
            onSelectId = {},
            description = "Lorem ipsum dolor sit amet.",
            leadingIcon = Icons.settings,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        PreferenceRadioGroupPickerChip(
            title = "Preference",
            selectedId = "item2",
            items = items,
            onSelectId = {},
            leadingIcon = Icons.settings,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        PreferenceRadioGroupPickerChip(
            title = "Preference",
            selectedId = null,
            items = items,
            onSelectId = {},
            leadingIcon = Icons.settings,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        PreferenceRadioGroupPickerChip(
            title = "Preference",
            selectedId = "item1",
            items = items,
            onSelectId = {},
            enabled = false,
            leadingIcon = Icons.settings,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}
