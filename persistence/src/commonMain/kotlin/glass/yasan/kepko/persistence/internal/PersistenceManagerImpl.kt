package glass.yasan.kepko.persistence.internal

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultLight
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme
import glass.yasan.kepko.persistence.PersistenceManager
import glass.yasan.kepko.persistence.PersistenceManager.Companion.STYLE_ID_SYSTEM

internal class PersistenceManagerImpl(
    private val settings: Settings,
) : PersistenceManager {

    internal companion object {
        @VisibleForTesting
        const val KEY_STYLE = "kepko.style.primary"

        @VisibleForTesting
        const val KEY_LIGHT_STYLE = "kepko.style.light"

        @VisibleForTesting
        const val KEY_DARK_STYLE = "kepko.style.dark"

        @VisibleForTesting
        const val KEY_GRAYSCALE = "kepko.grayscale"
    }

    @Composable
    override fun style(): ThemeStyle = resolvedStyle(isDark = isSystemInDarkTheme())

    internal fun resolvedStyle(isDark: Boolean): ThemeStyle =
        stylePrimary ?: if (isDark) styleDark else styleLight

    private var _stylePrimary by mutableStateOf(
        settings.getStringOrNull(KEY_STYLE)?.let { ThemeStyle.fromIdOrNull(it) }
    )
    override var stylePrimary: ThemeStyle?
        get() = _stylePrimary
        set(value) {
            _stylePrimary = value
            settings.putString(KEY_STYLE, value?.id ?: STYLE_ID_SYSTEM)
        }

    private var _styleLight by mutableStateOf(
        ThemeStyle.fromIdOrNull(settings.getStringOrNull(KEY_LIGHT_STYLE)) ?: defaultLight
    )
    override var styleLight: ThemeStyle
        get() = _styleLight
        set(value) {
            _styleLight = value
            settings.putString(KEY_LIGHT_STYLE, value.id)
        }

    private var _styleDark by mutableStateOf(
        ThemeStyle.fromIdOrNull(settings.getStringOrNull(KEY_DARK_STYLE)) ?: defaultDark
    )
    override var styleDark: ThemeStyle
        get() = _styleDark
        set(value) {
            _styleDark = value
            settings.putString(KEY_DARK_STYLE, value.id)
        }

    private var _grayscale by mutableStateOf(settings.getBoolean(KEY_GRAYSCALE, false))
    override var grayscale: Boolean
        get() = _grayscale
        set(value) {
            _grayscale = value
            settings.putBoolean(KEY_GRAYSCALE, value)
        }
}
