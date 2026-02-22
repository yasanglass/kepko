package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

@Composable
public actual fun rememberKepkoSettings(name: String): Settings =
    remember(name) {
        NSUserDefaultsSettings(NSUserDefaults(suiteName = name))
    }
