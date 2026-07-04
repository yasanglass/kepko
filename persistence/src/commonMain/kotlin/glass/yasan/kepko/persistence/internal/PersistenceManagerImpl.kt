package glass.yasan.kepko.persistence.internal

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.persistence.PersistenceManager
import glass.yasan.kepko.persistence.PersistenceManager.Companion.PALETTE_ID_SYSTEM
import glass.yasan.kepko.persistence.ProfilePersistenceManager

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
    }

    override val profileManager: ProfilePersistenceManager = ProfilePersistenceManagerImpl(settings)

    internal fun resolvedPalette(
        isSystemInDarkTheme: Boolean,
    ): ColorPalette = getPalettePrimary(null) ?: if (isSystemInDarkTheme) paletteDark else paletteLight

    private var _palettePrimary by mutableStateOf(
        settings.getStringOrNull(KEY_STYLE)?.let { ColorPalette.fromIdOrNull(it) }
    )

    override fun getPalettePrimary(profileId: String?): ColorPalette? {
        if (profileId != null) {
            val profilePalette = profileManager.getProfilePalette(profileId)
            if (profilePalette != null) return profilePalette
        }

        return _palettePrimary
    }

    override fun setPalettePrimary(profileId: String?, value: ColorPalette?) {
        if (profileId != null) {
            profileManager.setProfilePalette(profileId, value)
        } else {
            _palettePrimary = value
            settings.putString(KEY_STYLE, value?.id ?: PALETTE_ID_SYSTEM)
        }
    }

    private var _paletteLight by mutableStateOf(
        value = ColorPalette.fromIdOrNull(
            id = settings.getStringOrNull(KEY_LIGHT_STYLE)
        ) ?: getDefaultSnapshot().paletteLight
    )
    override var paletteLight: ColorPalette
        get() = _paletteLight
        set(value) {
            _paletteLight = value
            settings.putString(KEY_LIGHT_STYLE, value.id)
        }

    private var _paletteDark by mutableStateOf(
        value = ColorPalette.fromIdOrNull(
            id = settings.getStringOrNull(KEY_DARK_STYLE)
        ) ?: getDefaultSnapshot().paletteDark
    )
    override var paletteDark: ColorPalette
        get() = _paletteDark
        set(value) {
            _paletteDark = value
            settings.putString(KEY_DARK_STYLE, value.id)
        }

    private var _grayscale by mutableStateOf(
        settings.getBoolean(
            KEY_GRAYSCALE,
            getDefaultSnapshot().grayscale
        )
    )

    override fun isGrayscaleEnabled(profileId: String?): Boolean {
        if (profileId != null) {
            val profileGrayscale = profileManager.getProfileGrayscale(profileId)
            if (profileGrayscale != null) return profileGrayscale
        }

        return _grayscale
    }

    override fun setGrayscaleEnabled(profileId: String?, value: Boolean) {
        if (profileId != null) {
            profileManager.setProfileGrayscale(profileId, value)
        } else {
            _grayscale = value
            settings.putBoolean(KEY_GRAYSCALE, value)
        }
    }

    private var _outline by mutableStateOf(
        settings.getFloat(KEY_OUTLINE, getDefaultSnapshot().outline.value).dp
    )
    override var outline: Dp
        get() = _outline
        set(value) {
            _outline = value
            settings.putFloat(KEY_OUTLINE, value.value)
        }

    private var _roundness by mutableStateOf(
        settings.getFloat(KEY_ROUNDNESS, getDefaultSnapshot().roundness)
    )
    override var roundness: Float
        get() = _roundness
        set(value) {
            _roundness = value
            settings.putFloat(KEY_ROUNDNESS, value)
        }
}
