package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.dimension.Dimensions
import glass.yasan.kepko.foundation.shape.Shapes
import glass.yasan.kepko.foundation.theme.KepkoTheme

/**
 * Alternative to directly using [KepkoTheme] which internally handles the theme preferences.
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
    val kepkoThemeState: KepkoThemePersistence = rememberKepkoThemePersistence(settings)

    KepkoTheme(
        style = kepkoThemeState.style(),
        grayscale = kepkoThemeState.grayscale,
        dimensions = dimensions,
        shapes = shapes,
    ) {
        CompositionLocalProvider(
            value = LocalKepkoThemePersistence provides kepkoThemeState,
            content = content,
        )
    }
}
