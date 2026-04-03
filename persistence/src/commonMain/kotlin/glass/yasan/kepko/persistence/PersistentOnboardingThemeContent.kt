package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import glass.yasan.kepko.component.PreferenceAnnotation
import glass.yasan.kepko.component.PreferenceRadioGroup
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme
import glass.yasan.kepko.persistence.PersistenceManager.Companion.PALETTE_ID_SYSTEM
import glass.yasan.kepko.resource.Strings

/**
 * A simplified version of [PersistentPreferenceThemeContent] that is meant to be used during onboarding of your app.
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentOnboardingThemeContent(
    persistence: PersistenceManager = LocalKepkoPersistenceManager.current,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    modifier: Modifier = Modifier,
) {
    AnimatedPersistentKepkoTheme(
        persistence = persistence,
        isSystemInDarkTheme = isSystemInDarkTheme,
    ) {
        val systemItem = PreferenceRadioGroupItem(
            id = PALETTE_ID_SYSTEM,
            annotation = PreferenceAnnotation.default,
        ) { Strings.colorPaletteSystem }

        val paletteItems = ColorPalette.entries.map { palette ->
            palette.asOnboardingRadioGroupItem()
        }

        PreferenceRadioGroup(
            title = Strings.preferencePaletteTitle,
            selectedId = persistence.palettePrimary?.id ?: PALETTE_ID_SYSTEM,
            items = listOf(systemItem) + paletteItems,
            onSelectId = { id ->
                persistence.palettePrimary = if (id == PALETTE_ID_SYSTEM) {
                    null
                } else {
                    ColorPalette.fromIdOrNull(id)
                }
            },
            modifier = modifier,
        )
    }
}

private fun ColorPalette.asOnboardingRadioGroupItem(): PreferenceRadioGroupItem =
    PreferenceRadioGroupItem(
        id = id,
        segment = category.ordinal,
    ) {
        title()
    }

@ExperimentalKepkoApi
@PreviewWithTest
@Composable
internal fun PersistentOnboardingThemeContentSystemPreview() {
    PreviewPersistentKepkoTheme {
        PersistentOnboardingThemeContent()
    }
}

@ExperimentalKepkoApi
@PreviewWithTest
@Composable
internal fun PersistentOnboardingThemeContentSelectedPreview() {
    PreviewPersistentKepkoTheme(configure = { palettePrimary = ColorPalette.SOLARIZED_DARK }) {
        PersistentOnboardingThemeContent()
    }
}
