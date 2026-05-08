package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import glass.yasan.kepko.component.PreferenceRadioGroup
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultLight
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme
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
        PreferenceRadioGroup(
            title = Strings.preferencePaletteTitle,
            selectedId = persistence.selectedOnboardingThemeOptionId(),
            items = onboardingThemeOptions.map { option -> option.asPreferenceRadioGroupItem() },
            onSelectId = { id ->
                onboardingThemeOptions.firstOrNull { option -> option.id == id }
                    ?.let(persistence::applyOnboardingThemeOption)
            },
            modifier = modifier,
        )
    }
}

private data class OnboardingThemeOption(
    val id: String,
    val palettePrimary: ColorPalette?,
    val paletteLight: ColorPalette,
    val paletteDark: ColorPalette,
    val title: @Composable () -> String,
)

private const val DEFAULT_ONBOARDING_THEME_OPTION_ID = "default"

private val onboardingThemeOptions = listOf(
    OnboardingThemeOption(
        id = DEFAULT_ONBOARDING_THEME_OPTION_ID,
        palettePrimary = null,
        paletteLight = defaultLight,
        paletteDark = defaultDark,
    ) { Strings.preferenceOnboardingPaletteDefault },
    OnboardingThemeOption(
        id = "amoled",
        palettePrimary = ColorPalette.BLACK,
        paletteLight = defaultLight,
        paletteDark = defaultDark,
    ) { Strings.preferenceOnboardingPaletteAmoled },
    OnboardingThemeOption(
        id = "solarized",
        palettePrimary = null,
        paletteLight = ColorPalette.SOLARIZED_LIGHT,
        paletteDark = ColorPalette.SOLARIZED_DARK,
    ) { Strings.preferenceOnboardingPaletteSolarized },
    OnboardingThemeOption(
        id = "catppuccin",
        palettePrimary = null,
        paletteLight = ColorPalette.CATPPUCCIN_LATTE,
        paletteDark = ColorPalette.CATPPUCCIN_MOCHA,
    ) { Strings.preferenceOnboardingPaletteCatppuccin },
    OnboardingThemeOption(
        id = "gruvbox",
        palettePrimary = null,
        paletteLight = ColorPalette.GRUVBOX_LIGHT,
        paletteDark = ColorPalette.GRUVBOX_DARK,
    ) { Strings.preferenceOnboardingPaletteGruvbox },
)

private fun OnboardingThemeOption.asPreferenceRadioGroupItem(): PreferenceRadioGroupItem =
    PreferenceRadioGroupItem(
        id = id,
    ) {
        title()
    }

private fun PersistenceManager.selectedOnboardingThemeOptionId(): String =
    onboardingThemeOptions.firstOrNull { option ->
        if (option.palettePrimary != null) {
            palettePrimary == option.palettePrimary
        } else {
            palettePrimary == null &&
                paletteLight == option.paletteLight &&
                paletteDark == option.paletteDark
        }
    }?.id ?: DEFAULT_ONBOARDING_THEME_OPTION_ID

private fun PersistenceManager.applyOnboardingThemeOption(option: OnboardingThemeOption) {
    palettePrimary = option.palettePrimary
    paletteLight = option.paletteLight
    paletteDark = option.paletteDark
}

@ExperimentalKepkoApi
@PreviewWithTest
@Composable
internal fun PersistentOnboardingThemeContentSystemPreview() {
    PreviewPersistentKepkoTheme {
        PersistentOnboardingThemeContent(isSystemInDarkTheme = false)
    }
}

@ExperimentalKepkoApi
@PreviewWithTest
@Composable
internal fun PersistentOnboardingThemeContentSelectedPreview() {
    PreviewPersistentKepkoTheme(configure = {
        palettePrimary = null
        paletteLight = ColorPalette.SOLARIZED_LIGHT
        paletteDark = ColorPalette.SOLARIZED_DARK
    }) {
        PersistentOnboardingThemeContent()
    }
}
