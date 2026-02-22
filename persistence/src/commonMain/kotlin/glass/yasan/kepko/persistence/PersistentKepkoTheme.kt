package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.dimension.Dimensions
import glass.yasan.kepko.foundation.shape.Shapes
import glass.yasan.kepko.foundation.theme.KepkoTheme

/**
 * A [KepkoTheme] wrapper that automatically persists and restores theme preferences.
 *
 * @see rememberKepkoSettings
 * @see PersistenceManager
 * @see PersistentPreferenceThemeScreen
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentKepkoTheme(
    settings: Settings = rememberKepkoSettings(),
    dimensions: Dimensions = KepkoTheme.dimensions,
    shapes: Shapes = KepkoTheme.shapes,
    content: @Composable () -> Unit,
) {
    val persistenceManager: PersistenceManager = rememberPersistenceManager(settings)

    PersistentKepkoTheme(
        persistenceManager = persistenceManager,
        dimensions = dimensions,
        shapes = shapes,
        content = content,
    )
}

/**
 * A [KepkoTheme] wrapper that uses a pre-configured [PersistenceManager]
 * to persist and restore theme preferences.
 *
 * @see PersistenceManager
 * @see PersistentPreferenceThemeScreen
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentKepkoTheme(
    persistenceManager: PersistenceManager,
    dimensions: Dimensions = KepkoTheme.dimensions,
    shapes: Shapes = KepkoTheme.shapes,
    content: @Composable () -> Unit,
) {
    KepkoTheme(
        style = persistenceManager.style(),
        grayscale = persistenceManager.grayscale,
        dimensions = dimensions,
        shapes = shapes,
    ) {
        CompositionLocalProvider(
            value = LocalKepkoPersistenceManager provides persistenceManager,
            content = content,
        )
    }
}
