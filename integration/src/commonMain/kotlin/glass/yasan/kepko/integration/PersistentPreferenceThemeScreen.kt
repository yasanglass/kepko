package glass.yasan.kepko.integration

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.PreferenceAnnotation
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.PreferenceRadioGroupPicker
import glass.yasan.kepko.component.PreferenceSwitch
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultLight
import glass.yasan.kepko.integration.KepkoThemePersistence.Companion.STYLE_ID_SYSTEM
import glass.yasan.kepko.resource.Strings

/**
 * A theme preferences screen which allows easy integration when used with [PersistentKepkoTheme].
 *
 * Internally uses [KepkoThemePersistence] to handle the theme preferences.
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
        title = Strings.preferenceTitleTheme,
        onBackClick = onBackClick,
        modifier = modifier,
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
    val persistence = LocalKepkoThemePersistence.current
    val styleItems = ThemeStyle.entries.map { it.asPreferenceRadioGroupItem() }
    val systemItem = PreferenceRadioGroupItem(
        id = STYLE_ID_SYSTEM,
        annotation = PreferenceAnnotation.default,
    ) { Strings.themeStyleSystem }

    Column(
        modifier = modifier,
    ) {
        PreferenceRadioGroupPicker(
            title = Strings.preferenceTitleTheme,
            selectedId = persistence.stylePrimary?.id ?: STYLE_ID_SYSTEM,
            items = listOf(systemItem) + styleItems,
            onSelectId = { id ->
                persistence.stylePrimary = if (id == STYLE_ID_SYSTEM) {
                    null
                } else {
                    ThemeStyle.fromIdOrNull(id)
                }
            },
        )
        AnimatedVisibility(
            visible = persistence.stylePrimary == null,
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            Column {
                Spacer(Modifier.height(8.dp))
                PersistentPreferenceThemeLight(persistence)
                Spacer(Modifier.height(8.dp))
                PersistentPreferenceThemeDark(persistence)
            }
        }
        Spacer(Modifier.height(16.dp))
        PersistentPreferenceThemeGrayscale(persistence)
    }
}

@Composable
private fun PersistentPreferenceThemeLight(
    persistence: KepkoThemePersistence,
) {
    PreferenceRadioGroupPicker(
        title = Strings.preferenceTitleLightThemeStyle,
        selectedId = persistence.styleLight.id,
        items = ThemeStyle.entries.map { style ->
            val item = style.asPreferenceRadioGroupItem()
            val segmented = if (style.isDark) item.copy(segment = 1) else item
            if (style == defaultLight) segmented.copy(annotation = PreferenceAnnotation.default) else segmented
        },
        onSelectId = { id -> ThemeStyle.fromIdOrNull(id)?.let { persistence.styleLight = it } },
        description = Strings.preferenceDescriptionLightThemeStyle,
    )
}

@Composable
private fun PersistentPreferenceThemeDark(
    persistence: KepkoThemePersistence,
) {
    PreferenceRadioGroupPicker(
        title = Strings.preferenceTitleDarkThemeStyle,
        selectedId = persistence.styleDark.id,
        items = ThemeStyle.entries.map { style ->
            val item = style.asPreferenceRadioGroupItem()
            val segmented = if (!style.isDark) item.copy(segment = 1) else item
            if (style == defaultDark) segmented.copy(annotation = PreferenceAnnotation.default) else segmented
        },
        onSelectId = { id -> ThemeStyle.fromIdOrNull(id)?.let { persistence.styleDark = it } },
        description = Strings.preferenceDescriptionDarkThemeStyle,
    )
}

@Composable
private fun PersistentPreferenceThemeGrayscale(
    persistence: KepkoThemePersistence,
) {
    PreferenceSwitch(
        title = Strings.preferenceTitleGrayscale,
        checked = persistence.grayscale,
        onCheckedChange = { persistence.grayscale = it },
        annotation = PreferenceAnnotation.experimental,
    )
}

private fun ThemeStyle.asPreferenceRadioGroupItem(): PreferenceRadioGroupItem = PreferenceRadioGroupItem(
    id = id,
) {
    title()
}
