package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.dimension.Dimensions
import glass.yasan.kepko.foundation.shape.Shapes
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme
import glass.yasan.kepko.persistence.internal.PreviewPersistenceManager
import glass.yasan.kepko.persistence.internal.SingletonPersistenceManager

/**
 * A [KepkoTheme] wrapper that automatically persists and restores theme preferences.
 *
 * - Use [LocalKepkoThemeStyle] to access the active theme style in [content].
 * - Use [LocalKepkoPersistenceManager] to access the [PersistenceManager] instance in [content].
 * - Use [PersistentPreferenceThemeScreen] to allow users to configure theme preferences.
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentKepkoTheme(
    dimensions: Dimensions = KepkoTheme.dimensions,
    shapes: Shapes = KepkoTheme.shapes,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val persistenceManager = remember { SingletonPersistenceManager.instance }

    PersistentKepkoTheme(
        persistenceManager = persistenceManager,
        dimensions = dimensions,
        shapes = shapes,
        isSystemInDarkTheme = isSystemInDarkTheme,
        content = content,
    )
}

/**
 * A [KepkoTheme] wrapper that automatically persists and restores theme preferences.
 *
 * - Use [LocalKepkoThemeStyle] to access the active theme style in [content].
 * - Use [LocalKepkoPersistenceManager] to access the [PersistenceManager] instance in [content].
 * - Use [PersistentPreferenceThemeScreen] to allow users to configure theme preferences.
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentKepkoTheme(
    persistenceManager: PersistenceManager,
    dimensions: Dimensions = KepkoTheme.dimensions,
    shapes: Shapes = KepkoTheme.shapes,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val style = persistenceManager.activeStyle(
        isSystemInDarkTheme = isSystemInDarkTheme,
    )

    KepkoTheme(
        style = style,
        grayscale = persistenceManager.grayscale,
        dimensions = dimensions,
        shapes = shapes,
    ) {
        CompositionLocalProvider(
            LocalKepkoPersistenceManager provides persistenceManager,
            LocalKepkoThemeStyle provides style,
            content = content,
        )
    }
}

/**
 * A [PersistentKepkoTheme] implementation for preview purposes.
 *
 * The active [ThemeStyle] will always be based on the [isSystemInDarkTheme] parameter.
 *
 * @see PreviewPersistenceManager
 */
@Composable
public fun PreviewPersistentKepkoTheme(
    isSystemInDarkTheme: Boolean = false,
    configure: PreviewPersistenceManager.() -> Unit = {},
    content: @Composable () -> Unit,
) {
    PersistentKepkoTheme(
        persistenceManager = remember { PreviewPersistenceManager().apply(configure) },
        isSystemInDarkTheme = isSystemInDarkTheme,
        content = content,
    )
}
