package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import glass.yasan.kepko.foundation.dimension.DimensionTokens
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultLight
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme

@Stable
public interface PersistenceManager {

    public companion object {
        public const val PALETTE_ID_SYSTEM: String = "system"
    }

    /**
     * A snapshot of the current preferences.
     *
     * @see getDefaultSnapshot
     * @see applySnapshot
     * @see toSnapshot
     */
    @Immutable
    public data class Snapshot(
        val palettePrimary: ColorPalette?,
        val paletteLight: ColorPalette,
        val paletteDark: ColorPalette,
        val grayscale: Boolean,
        val outline: Dp,
        val roundness: Float,
    )

    public val profileManager: ProfilePersistenceManager

    public var paletteLight: ColorPalette
    public var paletteDark: ColorPalette
    public var outline: Dp
    public var roundness: Float

    public fun getPalettePrimary(profileId: String?): ColorPalette?

    public fun setPalettePrimary(profileId: String?, value: ColorPalette?)

    public fun isGrayscaleEnabled(profileId: String?): Boolean

    public fun setGrayscaleEnabled(profileId: String?, value: Boolean)

    public fun getDefaultSnapshot(): Snapshot = Snapshot(
        palettePrimary = null,
        paletteLight = defaultLight,
        paletteDark = defaultDark,
        grayscale = false,
        outline = DimensionTokens.borderThickness,
        roundness = 1f,
    )

    public fun toSnapshot(): Snapshot = Snapshot(
        palettePrimary = getPalettePrimary(profileId = null),
        paletteLight = paletteLight,
        paletteDark = paletteDark,
        grayscale = isGrayscaleEnabled(profileId = null),
        outline = outline,
        roundness = roundness,
    )

    public fun applySnapshot(snapshot: Snapshot) {
        setPalettePrimary(profileId = null, snapshot.palettePrimary)
        paletteLight = snapshot.paletteLight
        paletteDark = snapshot.paletteDark
        setGrayscaleEnabled(profileId = null, snapshot.grayscale)
        outline = snapshot.outline
        roundness = snapshot.roundness
    }

    /**
     * If the current preferences match the default preferences.
     *
     * @see getDefaultSnapshot
     */
    public val isDefault: Boolean
        get() = toSnapshot() == getDefaultSnapshot()

    /**
     * Resets the preferences to the default preferences.
     *
     * @see getDefaultSnapshot
     */
    public fun reset() {
        applySnapshot(getDefaultSnapshot())
    }

    @Composable
    public fun getActivePalette(
        profileId: String?,
        isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    ): ColorPalette = getPalettePrimary(profileId)
        ?: if (isSystemInDarkTheme) paletteDark else paletteLight

}

public val LocalKepkoPersistenceManager: ProvidableCompositionLocal<PersistenceManager> =
    compositionLocalOf {
        error("PersistenceManager is not provided. Wrap content in PersistentKepkoTheme.")
    }

public val LocalKepkoColorPalette: ProvidableCompositionLocal<ColorPalette> =
    compositionLocalOf {
        error("ColorPalette is not provided. Wrap content in PersistentKepkoTheme.")
    }
