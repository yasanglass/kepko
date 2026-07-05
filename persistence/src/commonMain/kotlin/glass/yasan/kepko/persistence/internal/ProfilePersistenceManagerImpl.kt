package glass.yasan.kepko.persistence.internal

import androidx.compose.runtime.mutableStateMapOf
import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.persistence.ProfilePersistenceManager

internal class ProfilePersistenceManagerImpl(
    private val settings: Settings,
) : ProfilePersistenceManager {

    private val palette = OverlaidProfileSetting(
        read = { profileId ->
            settings.getStringOrNull(profilePaletteKey(profileId))
                ?.let { id -> ColorPalette.fromIdOrNull(id) }
        },
        write = { profileId, value ->
            if (value != null) {
                settings.putString(profilePaletteKey(profileId), value.id)
            } else {
                settings.remove(profilePaletteKey(profileId))
            }
        },
    )

    private val grayscale = OverlaidProfileSetting(
        read = { profileId ->
            settings.getBooleanOrNull(profileGrayscaleKey(profileId))
        },
        write = { profileId, value ->
            if (value != null) {
                settings.putBoolean(profileGrayscaleKey(profileId), value)
            } else {
                settings.remove(profileGrayscaleKey(profileId))
            }
        },
    )

    override fun getProfilePalette(profileId: String): ColorPalette? = palette[profileId]

    override fun setProfilePalette(profileId: String, value: ColorPalette?) {
        palette[profileId] = value
    }

    override fun getProfileGrayscale(profileId: String): Boolean? = grayscale[profileId]

    override fun setProfileGrayscale(profileId: String, value: Boolean?) {
        grayscale[profileId] = value
    }

    private fun profilePaletteKey(profileId: String): String =
        "glass.yasan.kepko.style.primary.profile.$profileId"

    private fun profileGrayscaleKey(profileId: String): String =
        "glass.yasan.kepko.grayscale.profile.$profileId"

}

/**
 * Persisted per-profile value with an observable overlay of this session's writes, so Compose recomposes per key.
 */
private class OverlaidProfileSetting<T : Any>(
    private val read: (profileId: String) -> T?,
    private val write: (profileId: String, value: T?) -> Unit,
) {

    private val overlay = mutableStateMapOf<String, T?>()

    operator fun get(profileId: String): T? =
        if (profileId in overlay) overlay[profileId] else read(profileId)

    operator fun set(profileId: String, value: T?) {
        write(profileId, value)
        overlay[profileId] = value
    }

}
