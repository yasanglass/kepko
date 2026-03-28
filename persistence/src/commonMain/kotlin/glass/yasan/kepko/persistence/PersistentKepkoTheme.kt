package glass.yasan.kepko.persistence

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.dimension.Dimensions
import glass.yasan.kepko.foundation.shape.ShapeTokens
import glass.yasan.kepko.foundation.shape.Shapes
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme
import glass.yasan.kepko.persistence.internal.PreviewPersistenceManager
import glass.yasan.kepko.persistence.internal.SingletonPersistenceManager

/**
 * A [KepkoTheme] wrapper that automatically persists and restores theme preferences.
 *
 * - Use [LocalKepkoColorPalette] to access the active color palette in [content].
 * - Use [LocalKepkoPersistenceManager] to access the [PersistenceManager] instance in [content].
 * - Use [PersistentPreferenceThemeScreen] to allow users to configure theme preferences.
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentKepkoTheme(
    dimensions: Dimensions = KepkoTheme.dimensions,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val persistenceManager = remember { SingletonPersistenceManager.instance }

    PersistentKepkoTheme(
        persistenceManager = persistenceManager,
        dimensions = dimensions,
        isSystemInDarkTheme = isSystemInDarkTheme,
        content = content,
    )
}

/**
 * A [KepkoTheme] wrapper that automatically persists and restores theme preferences.
 *
 * - Use [LocalKepkoColorPalette] to access the active color palette in [content].
 * - Use [LocalKepkoPersistenceManager] to access the [PersistenceManager] instance in [content].
 * - Use [PersistentPreferenceThemeScreen] to allow users to configure theme preferences.
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentKepkoTheme(
    persistenceManager: PersistenceManager,
    dimensions: Dimensions = KepkoTheme.dimensions,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val palette = persistenceManager.activePalette(
        isSystemInDarkTheme = isSystemInDarkTheme,
    )

    val animatedOutline by animateDpAsState(persistenceManager.outline)
    val animatedRoundness by animateFloatAsState(persistenceManager.roundness)

    val resolvedDimensions = dimensions.copy(
        borderThickness = animatedOutline,
    )

    val roundness = animatedRoundness
    val resolvedShapes = Shapes(
        extraSmall = RoundedCornerShape(ShapeTokens.extraSmallCornerRadius * roundness),
        small = RoundedCornerShape(ShapeTokens.smallCornerRadius * roundness),
        medium = RoundedCornerShape(ShapeTokens.mediumCornerRadius * roundness),
        large = RoundedCornerShape(ShapeTokens.largeCornerRadius * roundness),
        extraLarge = RoundedCornerShape(ShapeTokens.extraLargeCornerRadius * roundness),
    )

    KepkoTheme(
        palette = palette,
        grayscale = persistenceManager.grayscale,
        dimensions = resolvedDimensions,
        shapes = resolvedShapes,
    ) {
        CompositionLocalProvider(
            LocalKepkoPersistenceManager provides persistenceManager,
            LocalKepkoColorPalette provides palette,
            content = content,
        )
    }
}

/**
 * A [PersistentKepkoTheme] implementation for preview purposes.
 *
 * The active [ColorPalette] will always be based on the [isSystemInDarkTheme] parameter.
 *
 * @see PreviewPersistenceManager
 */
@OptIn(ExperimentalKepkoApi::class)
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
