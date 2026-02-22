package glass.yasan.kepko.persistence

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.PreferenceAnnotation
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.PreferenceRadioGroupPicker
import glass.yasan.kepko.component.PreferenceSwitch
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultLight
import glass.yasan.kepko.persistence.PersistenceManager.Companion.STYLE_ID_SYSTEM
import androidx.annotation.VisibleForTesting
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
) {
    Scaffold(
        title = Strings.persistenceThemeTitle,
        onBackClick = onBackClick,
        modifier = modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.SCREEN)
    ) { contentPadding ->
        PersistentPreferenceThemeContent(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(contentPadding),
        )
    }
}

@Composable
private fun PersistentPreferenceThemeContent(
    modifier: Modifier = Modifier,
) {
    val persistence = LocalKepkoPersistenceManager.current
    val styleItems = ThemeStyle.entries.map { it.asPreferenceRadioGroupItem() }
    val systemItem = PreferenceRadioGroupItem(
        id = STYLE_ID_SYSTEM,
        annotation = PreferenceAnnotation.default,
    ) { Strings.themeStyleSystem }

    Column(
        modifier = modifier,
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
            modifier = Modifier
                .testTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
        )
        AnimatedVisibility(
            visible = persistence.stylePrimary == null,
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Spacer(Modifier.width(32.dp))
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Spacer(Modifier.height(8.dp))
                    PersistentPreferenceThemeLight(persistence)
                    Spacer(Modifier.height(8.dp))
                    PersistentPreferenceThemeDark(persistence)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        PersistentPreferenceThemeGrayscale(persistence)
    }
}

@Composable
private fun PersistentPreferenceThemeLight(
    persistence: PersistenceManager,
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
        modifier = Modifier
            .fillMaxWidth()
            .testTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER)
    )
}

@Composable
private fun PersistentPreferenceThemeDark(
    persistence: PersistenceManager,
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
        modifier = Modifier
            .fillMaxWidth()
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
internal fun PersistentPreferenceThemeScreenSystemPreview() {
    val persistence = rememberPersistenceManager()

    PersistentKepkoTheme(persistenceManager = persistence) {
        PersistentPreferenceThemeScreen(onBackClick = {})
    }
}

@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeScreenStyleSelectedPreview() {
    val persistence = rememberPersistenceManager().apply {
        stylePrimary = ThemeStyle.LIGHT
    }

    PersistentKepkoTheme(persistenceManager = persistence) {
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

