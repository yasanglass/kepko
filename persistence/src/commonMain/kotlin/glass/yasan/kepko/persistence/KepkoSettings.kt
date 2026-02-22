package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import com.russhwolf.settings.Settings

/**
 * Creates and remembers a [Settings] instance.
 *
 * @param name Used to scope the settings storage on platforms that support it (Android, Apple, JVM).
 * Ignored on web targets (JS, WasmJs) where [Settings] is backed by `localStorage`.
 */
@Composable
public expect fun rememberKepkoSettings(
    name: String = "kepko",
): Settings
