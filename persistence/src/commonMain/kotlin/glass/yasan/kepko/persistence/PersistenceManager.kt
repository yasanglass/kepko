package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme

@Stable
public interface PersistenceManager {

    public companion object {
        public const val PALETTE_ID_SYSTEM: String = "system"
    }

    public var palettePrimary: ColorPalette?
    public var paletteLight: ColorPalette
    public var paletteDark: ColorPalette
    public var grayscale: Boolean
    public var outline: Dp

    /**
     * @return active [ColorPalette] based on the persisted data and system theme.
     */
    @Composable
    public fun activePalette(
        isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    ): ColorPalette = palettePrimary ?: if (isSystemInDarkTheme) paletteDark else paletteLight

}

public val LocalKepkoPersistenceManager: ProvidableCompositionLocal<PersistenceManager> =
    compositionLocalOf {
        error("PersistenceManager is not provided. Wrap content in PersistentKepkoTheme.")
    }

public val LocalKepkoColorPalette: ProvidableCompositionLocal<ColorPalette> =
    compositionLocalOf {
        error("ColorPalette is not provided. Wrap content in PersistentKepkoTheme.")
    }
