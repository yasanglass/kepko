package glass.yasan.kepko.persistence.internal

import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultLight
import glass.yasan.kepko.persistence.PersistenceManager
import glass.yasan.kepko.persistence.PreviewPersistentKepkoTheme

/**
 * A [PersistenceManager] implementation for preview purposes.
 *
 * @see PreviewPersistentKepkoTheme
 */
public class PreviewPersistenceManager : PersistenceManager {

    override var palettePrimary: ColorPalette? = null
    override var paletteLight: ColorPalette = defaultLight
    override var paletteDark: ColorPalette = defaultDark
    override var grayscale: Boolean = false

    @Composable
    override fun activePalette(
        isSystemInDarkTheme: Boolean,
    ): ColorPalette = if (isSystemInDarkTheme) {
        paletteDark
    } else {
        paletteLight
    }

}
