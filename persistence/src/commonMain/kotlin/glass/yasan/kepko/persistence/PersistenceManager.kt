package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme

@Stable
public interface PersistenceManager {

    public companion object {
        public const val STYLE_ID_SYSTEM: String = "system"
    }

    public var stylePrimary: ThemeStyle?
    public var styleLight: ThemeStyle
    public var styleDark: ThemeStyle
    public var grayscale: Boolean

    /**
     * @return active theme style based on the persisted data and system theme.
     */
    @Composable
    public fun activeStyle(
        isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    ): ThemeStyle = stylePrimary ?: if (isSystemInDarkTheme) styleDark else styleLight

}

public val LocalKepkoPersistenceManager: ProvidableCompositionLocal<PersistenceManager> =
    compositionLocalOf {
        error("PersistenceManager is not provided. Wrap content in PersistentKepkoTheme.")
    }

public val LocalKepkoThemeStyle: ProvidableCompositionLocal<ThemeStyle> =
    compositionLocalOf {
        error("ThemeStyle is not provided. Wrap content in PersistentKepkoTheme.")
    }
