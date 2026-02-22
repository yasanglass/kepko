package glass.yasan.kepko.persistence.internal

import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ThemeStyle.Companion.defaultLight
import glass.yasan.kepko.persistence.PersistenceManager
import glass.yasan.kepko.persistence.PreviewPersistentKepkoTheme

/**
 * A [PersistenceManager] implementation for preview purposes.
 *
 * @see PreviewPersistentKepkoTheme
 */
public class PreviewPersistenceManager : PersistenceManager {

    override var stylePrimary: ThemeStyle? = null
    override var styleLight: ThemeStyle = defaultLight
    override var styleDark: ThemeStyle = defaultDark
    override var grayscale: Boolean = false

    @Composable
    override fun activeStyle(
        isSystemInDarkTheme: Boolean,
    ): ThemeStyle = if (isSystemInDarkTheme) {
        styleDark
    } else {
        styleLight
    }

}
