package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.resource.Icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun PreferenceRadioGroupPicker(
    title: String,
    selectedId: String?,
    items: List<PreferenceRadioGroupItem>,
    onSelectId: (String) -> Unit,
    leadingIcon: Painter,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    description: String? = null,
    annotation: PreferenceAnnotation? = null,
    enabled: Boolean = true,
    closeOnSelection: Boolean = true,
) {
    PreferenceRadioGroupPicker(
        title = title,
        selectedId = selectedId,
        items = items,
        onSelectId = onSelectId,
        modifier = modifier,
        sheetState = sheetState,
        description = description,
        annotation = annotation,
        enabled = enabled,
        closeOnSelection = closeOnSelection,
        leadingContent = {
            Icon(
                painter = leadingIcon,
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp),
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun PreferenceRadioGroupPicker(
    title: String,
    selectedId: String?,
    items: List<PreferenceRadioGroupItem>,
    onSelectId: (String) -> Unit,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    description: String? = null,
    annotation: PreferenceAnnotation? = null,
    enabled: Boolean = true,
    closeOnSelection: Boolean = true,
) {
    PreferenceRadioGroupPicker(
        title = title,
        selectedId = selectedId,
        items = items,
        onSelectId = onSelectId,
        modifier = modifier,
        sheetState = sheetState,
        description = description,
        annotation = annotation,
        enabled = enabled,
        closeOnSelection = closeOnSelection,
        leadingContent = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp),
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun PreferenceRadioGroupPicker(
    title: String,
    selectedId: String?,
    items: List<PreferenceRadioGroupItem>,
    onSelectId: (String) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    description: String? = null,
    annotation: PreferenceAnnotation? = null,
    enabled: Boolean = true,
    closeOnSelection: Boolean = true,
    leadingContent: @Composable () -> Unit = {},
) {
    var showSheet by remember { mutableStateOf(false) }

    ButtonText(
        text = title,
        onClick = { showSheet = true },
        enabled = enabled,
        annotation = annotation,
        modifier = modifier,
        leadingContent = { leadingContent() },
        trailingContent = {
            val selectedTitle = items.firstOrNull { it.id == selectedId }?.title()
            if (selectedTitle != null) {
                Text(
                    text = selectedTitle,
                    color = KepkoTheme.colors.contentSubtle,
                    modifier = Modifier.padding(start = 8.dp),
                )
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
internal fun PreferenceRadioGroupPickerLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupPickerDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupPickerBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupPickerSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioGroupPickerSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PreviewContent() {
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
        PreferenceRadioGroupPicker(
            title = "Preference",
            selectedId = "item1",
            items = items,
            onSelectId = {},
            description = "Lorem ipsum dolor sit amet.",
            annotation = PreferenceAnnotation.beta,
            leadingIcon = Icons.settings,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        PreferenceRadioGroupPicker(
            title = "Preference",
            selectedId = "item2",
            items = items,
            onSelectId = {},
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        PreferenceRadioGroupPicker(
            title = "Preference",
            selectedId = "item1",
            items = items,
            onSelectId = {},
            enabled = false,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}
