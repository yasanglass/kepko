package glass.yasan.kepko.integration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.integration.internal.KepkoThemePersistenceImpl

public interface KepkoThemePersistence {

    public companion object {
        internal const val STYLE_ID_SYSTEM = "system"
    }

    public var stylePrimary: ThemeStyle?
    public var styleLight: ThemeStyle
    public var styleDark: ThemeStyle
    public var grayscale: Boolean

    /**
     * @return resolved active theme style based on the persisted data.
     */
    @Composable
    public fun style(): ThemeStyle

}

internal val LocalKepkoThemePersistence: ProvidableCompositionLocal<KepkoThemePersistence> =
    compositionLocalOf {
        error("KepkoThemePersistence is not provided. Wrap content in PersistentKepkoTheme.")
    }

@Composable
public fun rememberKepkoThemePersistence(
    settings: Settings = remember { Settings() },
): KepkoThemePersistence =
    remember(settings) {
        KepkoThemePersistenceImpl(settings)
    }
