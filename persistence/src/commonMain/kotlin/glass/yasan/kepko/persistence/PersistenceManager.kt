package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl

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
     * @return resolved active theme style based on the persisted data.
     */
    @Composable
    public fun style(): ThemeStyle

}

public val LocalKepkoPersistenceManager: ProvidableCompositionLocal<PersistenceManager> =
    compositionLocalOf {
        error("PersistenceManager is not provided. Wrap content in PersistentKepkoTheme.")
    }

@Composable
public fun rememberPersistenceManager(
    settings: Settings = rememberKepkoSettings(),
): PersistenceManager =
    remember(settings) {
        PersistenceManagerImpl(settings)
    }
