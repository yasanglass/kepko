package glass.yasan.kepko.persistence

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.IconButton
import glass.yasan.kepko.component.PreferenceAnnotation
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.PreferenceRadioGroupPicker
import glass.yasan.kepko.component.PreferenceSwitch
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultLight
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme
import glass.yasan.kepko.persistence.PersistenceManager.Companion.STYLE_ID_SYSTEM
import glass.yasan.kepko.resource.Icons
import glass.yasan.kepko.resource.Strings

/**
 * A theme preferences screen which allows easy integration when used with [PersistentKepkoTheme].
 *
 * This preference screen allows the user to select different [ThemeStyle] combinations and toggle grayscale.
 *
 * @throws IllegalStateException in case this is used outside of [PersistentKepkoTheme].
 */
@Composable
public fun PersistentPreferenceThemeScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    Scaffold(
        title = Strings.persistenceThemeTitle,
        onBackClick = onBackClick,
        modifier = modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.SCREEN)
    ) { contentPadding ->
        PersistentPreferenceThemeContent(
            isSystemInDarkTheme = isSystemInDarkTheme,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(contentPadding),
        )
    }
}

@Composable
private fun PersistentPreferenceThemeContent(
    isSystemInDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    val persistence = LocalKepkoPersistenceManager.current
    val styleItems = ThemeStyle.entries.map { it.asPreferenceRadioGroupItem() }
    val systemItem = PreferenceRadioGroupItem(
        id = STYLE_ID_SYSTEM,
        annotation = PreferenceAnnotation.default,
    ) { Strings.themeStyleSystem }

    var colorPaletteStyle by remember { mutableStateOf<ThemeStyle?>(null) }

    colorPaletteStyle?.let { style ->
        ColorPaletteBottomSheet(
            style = style,
            grayscale = persistence.grayscale,
            onDismissRequest = { colorPaletteStyle = null },
        )
    }

    Column(
        modifier = modifier,
    ) {
        PersistentPreferenceThemePrimary(
            persistence = persistence,
            styleItems = styleItems,
            systemItem = systemItem,
            onShowColorPalette = { colorPaletteStyle = it },
        )
        AnimatedVisibility(
            visible = persistence.stylePrimary == null,
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            PersistentPreferenceThemeSystem(
                persistence = persistence,
                isSystemInDarkTheme = isSystemInDarkTheme,
                onShowColorPalette = { colorPaletteStyle = it },
            )
        }
        Spacer(Modifier.height(8.dp))
        PersistentPreferenceThemeGrayscale(persistence)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersistentPreferenceThemePrimary(
    persistence: PersistenceManager,
    styleItems: List<PreferenceRadioGroupItem>,
    systemItem: PreferenceRadioGroupItem,
    onShowColorPalette: (ThemeStyle) -> Unit,
) {
    val primaryStyle = persistence.stylePrimary
    var lastPrimaryStyle by remember { mutableStateOf(primaryStyle) }
    if (primaryStyle != null) lastPrimaryStyle = primaryStyle

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PreferenceRadioGroupPicker(
            title = Strings.persistenceThemeTitle,
            selectedId = persistence.stylePrimary?.id ?: STYLE_ID_SYSTEM,
            items = listOf(systemItem) + styleItems,
            onSelectId = { id ->
                persistence.stylePrimary = if (id == STYLE_ID_SYSTEM) {
                    null
                } else {
                    ThemeStyle.fromIdOrNull(id)
                }
            },
            leadingIcon = Icons.palette,
            modifier = Modifier
                .weight(1f)
                .testTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
        )

        AnimatedVisibility(
            visible = primaryStyle != null,
            enter = expandHorizontally(),
            exit = shrinkHorizontally(),
        ) {
            IconButton(
                painter = Icons.info,
                contentDescription = Strings.persistenceColorPaletteTitle,
                onClick = { lastPrimaryStyle?.let { onShowColorPalette(it) } },
            )
        }
    }
}

@Composable
private fun PersistentPreferenceThemeSystem(
    persistence: PersistenceManager,
    isSystemInDarkTheme: Boolean,
    onShowColorPalette: (ThemeStyle) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(Modifier.width(32.dp))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Spacer(Modifier.height(8.dp))
            PersistentPreferenceThemeStyleRow(
                persistence = persistence,
                isSystemInDarkTheme = isSystemInDarkTheme,
                isLight = true,
                onShowColorPalette = { onShowColorPalette(persistence.styleLight) },
            )
            Spacer(Modifier.height(8.dp))
            PersistentPreferenceThemeStyleRow(
                persistence = persistence,
                isSystemInDarkTheme = isSystemInDarkTheme,
                isLight = false,
                onShowColorPalette = { onShowColorPalette(persistence.styleDark) },
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun PersistentPreferenceThemeStyleRow(
    persistence: PersistenceManager,
    isSystemInDarkTheme: Boolean,
    isLight: Boolean,
    onShowColorPalette: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isLight) {
            PersistentPreferenceThemeLight(
                persistence = persistence,
                isSystemInDarkTheme = isSystemInDarkTheme,
                modifier = Modifier.weight(1f),
            )
        } else {
            PersistentPreferenceThemeDark(
                persistence = persistence,
                isSystemInDarkTheme = isSystemInDarkTheme,
                modifier = Modifier.weight(1f),
            )
        }
        IconButton(
            painter = Icons.info,
            contentDescription = Strings.persistenceColorPaletteTitle,
            onClick = onShowColorPalette,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersistentPreferenceThemeLight(
    persistence: PersistenceManager,
    isSystemInDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    PreferenceRadioGroupPicker(
        title = Strings.persistenceLightThemeStyleTitle,
        selectedId = persistence.styleLight.id,
        items = ThemeStyle.entries.map { style ->
            val item = style.asPreferenceRadioGroupItem()
            val segmented = if (style.isDark) item.copy(segment = 1) else item
            if (style == defaultLight) segmented.copy(annotation = PreferenceAnnotation.default) else segmented
        },
        onSelectId = { id -> ThemeStyle.fromIdOrNull(id)?.let { persistence.styleLight = it } },
        description = Strings.persistenceLightThemeStyleDescription,
        annotation = PreferenceAnnotation.active.takeIf { !isSystemInDarkTheme },
        leadingIcon = Icons.lightMode,
        modifier = modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersistentPreferenceThemeDark(
    persistence: PersistenceManager,
    isSystemInDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    PreferenceRadioGroupPicker(
        title = Strings.persistenceDarkThemeStyleTitle,
        selectedId = persistence.styleDark.id,
        items = ThemeStyle.entries.map { style ->
            val item = style.asPreferenceRadioGroupItem()
            val segmented = if (!style.isDark) item.copy(segment = 1) else item
            if (style == defaultDark) segmented.copy(annotation = PreferenceAnnotation.default) else segmented
        },
        onSelectId = { id -> ThemeStyle.fromIdOrNull(id)?.let { persistence.styleDark = it } },
        description = Strings.persistenceDarkThemeStyleDescription,
        annotation = PreferenceAnnotation.active.takeIf { isSystemInDarkTheme },
        leadingIcon = Icons.modeNight,
        modifier = modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER)
    )
}

@Composable
private fun PersistentPreferenceThemeGrayscale(
    persistence: PersistenceManager,
) {
    PreferenceSwitch(
        title = Strings.persistenceGrayscaleTitle,
        checked = persistence.grayscale,
        onCheckedChange = { persistence.grayscale = it },
        leadingIcon = Icons.filterBw,
        annotation = PreferenceAnnotation.experimental,
        modifier = Modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.GRAYSCALE)
    )
}

private fun ThemeStyle.asPreferenceRadioGroupItem(): PreferenceRadioGroupItem = PreferenceRadioGroupItem(
    id = id,
) {
    title()
}

@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeScreenSystemLightPreview() {
    PreviewPersistentKepkoTheme(configure = { styleLight = defaultLight; styleDark = defaultLight }) {
        PersistentPreferenceThemeScreen(onBackClick = {}, isSystemInDarkTheme = false)
    }
}

@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeScreenSystemDarkPreview() {
    PreviewPersistentKepkoTheme(
        isSystemInDarkTheme = true,
        configure = { styleLight = defaultDark; styleDark = defaultDark }
    ) {
        PersistentPreferenceThemeScreen(onBackClick = {}, isSystemInDarkTheme = true)
    }
}

@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeScreenStyleSelectedPreview() {
    PreviewPersistentKepkoTheme(configure = { stylePrimary = ThemeStyle.LIGHT }) {
        PersistentPreferenceThemeScreen(onBackClick = {})
    }
}

@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeScreenGrayscalePreview() {
    PreviewPersistentKepkoTheme(configure = { grayscale = true }) {
        PersistentPreferenceThemeScreen(onBackClick = {})
    }
}

@VisibleForTesting
internal object PersistentPreferenceThemeScreenSemantics {
    const val SCREEN = "theme_screen"
    const val STYLE_PICKER = "theme_style_picker"
    const val LIGHT_PICKER = "theme_light_picker"
    const val DARK_PICKER = "theme_dark_picker"
    const val GRAYSCALE = "theme_grayscale"
}
