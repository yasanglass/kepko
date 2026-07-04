package glass.yasan.kepko.persistence.internal

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.unit.Dp
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.persistence.PersistenceManager
import glass.yasan.kepko.persistence.PreviewPersistentKepkoTheme
import glass.yasan.kepko.persistence.ProfilePersistenceManager

/**
 * A [PersistenceManager] implementation for preview purposes.
 *
 * @see PreviewPersistentKepkoTheme
 */
public class PreviewPersistenceManager : PersistenceManager {

    override val profileManager: ProfilePersistenceManager = PreviewProfilePersistenceManager()
    override var paletteLight: ColorPalette = getDefaultSnapshot().paletteLight
    override var paletteDark: ColorPalette = getDefaultSnapshot().paletteDark
    override var outline: Dp = getDefaultSnapshot().outline
    override var roundness: Float = getDefaultSnapshot().roundness

    private var palettePrimary: ColorPalette? = getDefaultSnapshot().palettePrimary
    private var grayscale: Boolean = getDefaultSnapshot().grayscale

    override fun getPalettePrimary(profileId: String?): ColorPalette? {
        if (profileId != null) {
            val profilePalette = profileManager.getProfilePalette(profileId)
            if (profilePalette != null) return profilePalette
        }

        return palettePrimary
    }

    override fun setPalettePrimary(profileId: String?, value: ColorPalette?) {
        if (profileId != null) {
            profileManager.setProfilePalette(profileId, value)
        } else {
            palettePrimary = value
        }
    }

    override fun isGrayscaleEnabled(profileId: String?): Boolean {
        if (profileId != null) {
            val profileGrayscale = profileManager.getProfileGrayscale(profileId)
            if (profileGrayscale != null) return profileGrayscale
        }

        return grayscale
    }

    override fun setGrayscaleEnabled(profileId: String?, value: Boolean) {
        if (profileId != null) {
            profileManager.setProfileGrayscale(profileId, value)
        } else {
            grayscale = value
        }
    }

}

internal class PreviewProfilePersistenceManager : ProfilePersistenceManager {

    private val palettes = mutableStateMapOf<String, ColorPalette>()
    private val grayscales = mutableStateMapOf<String, Boolean>()

    override fun getProfilePalette(profileId: String): ColorPalette? = palettes[profileId]

    override fun setProfilePalette(profileId: String, value: ColorPalette?) {
        if (value != null) palettes[profileId] = value else palettes.remove(profileId)
    }

    override fun getProfileGrayscale(profileId: String): Boolean? = grayscales[profileId]

    override fun setProfileGrayscale(profileId: String, value: Boolean?) {
        if (value != null) grayscales[profileId] = value else grayscales.remove(profileId)
    }

}
