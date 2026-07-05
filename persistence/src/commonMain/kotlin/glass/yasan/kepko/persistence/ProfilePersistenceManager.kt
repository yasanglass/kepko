package glass.yasan.kepko.persistence

import androidx.compose.runtime.Stable
import glass.yasan.kepko.foundation.theme.ColorPalette

@Stable
public interface ProfilePersistenceManager {
    public fun getProfilePalette(profileId: String): ColorPalette?
    public fun setProfilePalette(profileId: String, value: ColorPalette?)
    public fun getProfileGrayscale(profileId: String): Boolean?
    public fun setProfileGrayscale(profileId: String, value: Boolean?)
}
