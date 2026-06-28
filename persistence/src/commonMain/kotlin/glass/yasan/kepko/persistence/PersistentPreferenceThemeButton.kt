package glass.yasan.kepko.persistence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.Button
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.persistence.internal.PreviewPersistenceManager
import glass.yasan.kepko.resource.Icons
import glass.yasan.kepko.resource.Strings

/**
 * A ready-made navigation button, with the active theme preferences shown in the description.
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentPreferenceThemeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    persistence: PersistenceManager = LocalKepkoPersistenceManager.current,
    text: String = Strings.preferenceThemeScreenTitle,
    description: String = persistence.themeButtonDescription(),
) {
    Button(
        text = text,
        description = description,
        onClick = onClick,
        leadingIcon = Icons.palette,
        trailingIcon = Icons.chevronForward,
        modifier = modifier,
    )
}

@ExperimentalKepkoApi
@Composable
public fun PersistenceManager.themeButtonDescription(): String {
    val paletteDescription = palettePrimary?.title?.invoke()
        ?: "${paletteLight.title()} / ${paletteDark.title()}"
    val descriptions = listOf(paletteDescription) +
            listOfNotNull(Strings.preferenceGrayscaleTitle.takeIf { grayscale })

    return descriptions.joinToString(", ")
}

@ExperimentalKepkoApi
@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp),
    ) {
        PreviewPreferenceThemeButton()
        PreviewPreferenceThemeButton(
            isSystemInDarkTheme = true,
            configure = {
                palettePrimary = ColorPalette.SOLARIZED_DARK
                paletteDark = ColorPalette.SOLARIZED_DARK
            },
        )
        PreviewPreferenceThemeButton(
            configure = {
                paletteLight = ColorPalette.SOLARIZED_LIGHT
                paletteDark = ColorPalette.BLACK
            },
        )
        PreviewPreferenceThemeButton(
            configure = { grayscale = true },
        )
    }
}

@ExperimentalKepkoApi
@Composable
private fun PreviewPreferenceThemeButton(
    isSystemInDarkTheme: Boolean = false,
    configure: PreviewPersistenceManager.() -> Unit = {},
) {
    PreviewPersistentKepkoTheme(
        isSystemInDarkTheme = isSystemInDarkTheme,
        configure = configure,
    ) {
        PersistentPreferenceThemeButton(onClick = {})
    }
}
