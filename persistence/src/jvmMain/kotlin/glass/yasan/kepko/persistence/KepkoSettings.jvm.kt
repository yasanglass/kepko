package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

@Composable
public actual fun rememberKepkoSettings(name: String): Settings =
    remember(name) {
        PreferencesSettings(Preferences.userRoot().node(name))
    }
