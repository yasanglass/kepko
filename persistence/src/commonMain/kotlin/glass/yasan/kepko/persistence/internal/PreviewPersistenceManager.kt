package glass.yasan.kepko.persistence.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.persistence.PersistenceManager
import glass.yasan.kepko.persistence.PreviewPersistentKepkoTheme

/**
 * A [PersistenceManager] implementation for preview purposes.
 *
 * @see PreviewPersistentKepkoTheme
 */
public class PreviewPersistenceManager : PersistenceManager {

    override var palettePrimary: ColorPalette? = getDefaultSnapshot().palettePrimary
    override var paletteLight: ColorPalette = getDefaultSnapshot().paletteLight
    override var paletteDark: ColorPalette = getDefaultSnapshot().paletteDark
    override var grayscale: Boolean = getDefaultSnapshot().grayscale
    override var outline: Dp = getDefaultSnapshot().outline
    override var roundness: Float = getDefaultSnapshot().roundness

    @Composable
    override fun activePalette(
        isSystemInDarkTheme: Boolean,
    ): ColorPalette = if (isSystemInDarkTheme) {
        paletteDark
    } else {
        paletteLight
    }

}
