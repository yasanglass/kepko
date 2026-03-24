package glass.yasan.kepko.persistence.internal

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.dimension.DimensionTokens
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultLight
import glass.yasan.kepko.persistence.PersistenceManager
import glass.yasan.kepko.persistence.PersistenceManager.Companion.PALETTE_ID_SYSTEM

internal class PersistenceManagerImpl(
    private val settings: Settings,
) : PersistenceManager {

    internal companion object {
        private const val PREFIX = "glass.yasan.kepko"

        @VisibleForTesting
        const val KEY_STYLE = "$PREFIX.style.primary"

        @VisibleForTesting
        const val KEY_LIGHT_STYLE = "$PREFIX.style.light"

        @VisibleForTesting
        const val KEY_DARK_STYLE = "$PREFIX.style.dark"

        @VisibleForTesting
        const val KEY_GRAYSCALE = "$PREFIX.grayscale"

        @VisibleForTesting
        const val KEY_OUTLINE = "$PREFIX.dimension.outline"

        @VisibleForTesting
        const val KEY_ROUNDNESS = "$PREFIX.dimension.roundness"

        private const val DEFAULT_ROUNDNESS = 1f
    }

    @Composable
    override fun activePalette(
        isSystemInDarkTheme: Boolean,
    ): ColorPalette = resolvedPalette(isSystemInDarkTheme)

    internal fun resolvedPalette(
        isSystemInDarkTheme: Boolean,
    ): ColorPalette = palettePrimary ?: if (isSystemInDarkTheme) paletteDark else paletteLight

    private var _palettePrimary by mutableStateOf(
        settings.getStringOrNull(KEY_STYLE)?.let { ColorPalette.fromIdOrNull(it) }
    )
    override var palettePrimary: ColorPalette?
        get() = _palettePrimary
        set(value) {
            _palettePrimary = value
            settings.putString(KEY_STYLE, value?.id ?: PALETTE_ID_SYSTEM)
        }

    private var _paletteLight by mutableStateOf(
        ColorPalette.fromIdOrNull(settings.getStringOrNull(KEY_LIGHT_STYLE)) ?: defaultLight
    )
    override var paletteLight: ColorPalette
        get() = _paletteLight
        set(value) {
            _paletteLight = value
            settings.putString(KEY_LIGHT_STYLE, value.id)
        }

    private var _paletteDark by mutableStateOf(
        ColorPalette.fromIdOrNull(settings.getStringOrNull(KEY_DARK_STYLE)) ?: defaultDark
    )
    override var paletteDark: ColorPalette
        get() = _paletteDark
        set(value) {
            _paletteDark = value
            settings.putString(KEY_DARK_STYLE, value.id)
        }

    private var _grayscale by mutableStateOf(settings.getBoolean(KEY_GRAYSCALE, false))
    override var grayscale: Boolean
        get() = _grayscale
        set(value) {
            _grayscale = value
            settings.putBoolean(KEY_GRAYSCALE, value)
        }

    private var _outline by mutableStateOf(
        settings.getFloat(KEY_OUTLINE, DimensionTokens.borderThickness.value).dp
    )
    override var outline: Dp
        get() = _outline
        set(value) {
            _outline = value
            settings.putFloat(KEY_OUTLINE, value.value)
        }

    private var _roundness by mutableStateOf(
        settings.getFloat(KEY_ROUNDNESS, DEFAULT_ROUNDNESS)
    )
    override var roundness: Float
        get() = _roundness
        set(value) {
            _roundness = value
            settings.putFloat(KEY_ROUNDNESS, value)
        }
}
