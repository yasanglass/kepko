package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.dimension.Dimensions
import glass.yasan.kepko.foundation.shape.Shapes
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons.settings

/**
 * Alternative to directly using [KepkoTheme]
 * which provides easier integration by handling theme preferences on its own.
 *
 * Use this alongside [PersistentPreferenceThemeScreen] to easily integrate Kepko with your app.
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentKepkoTheme(
    settings: Settings = remember { Settings() },
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
            value = LocalPersistenceManager provides persistenceManager,
            content = content,
        )
    }
}
