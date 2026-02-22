package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings

@Composable
public actual fun rememberKepkoSettings(name: String): Settings =
    remember(name) { StorageSettings() }
