package glass.yasan.kepko.persistence

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import glass.yasan.kepko.foundation.annotation.InternalKepkoApi
import glass.yasan.kepko.foundation.shape.ShapeTokens
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme

@InternalKepkoApi
@Composable
public fun AnimatedPersistentKepkoTheme(
    persistence: PersistenceManager = LocalKepkoPersistenceManager.current,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    profileId: String? = null,
    content: @Composable () -> Unit,
) {
    val palette = persistence.getActivePalette(
        isSystemInDarkTheme = isSystemInDarkTheme,
        profileId = profileId,
    )

    val animatedOutline by animateDpAsState(persistence.outline)
    val animatedRoundness by animateFloatAsState(persistence.roundness)

    val animatedDimensions = KepkoTheme.dimensions.copy(
        borderThickness = animatedOutline,
    )
    val animatedShapes = KepkoTheme.shapes.copy(
        extraSmall = RoundedCornerShape(ShapeTokens.extraSmallCornerRadius * animatedRoundness),
        small = RoundedCornerShape(ShapeTokens.smallCornerRadius * animatedRoundness),
        medium = RoundedCornerShape(ShapeTokens.mediumCornerRadius * animatedRoundness),
        large = RoundedCornerShape(ShapeTokens.largeCornerRadius * animatedRoundness),
        extraLarge = RoundedCornerShape(ShapeTokens.extraLargeCornerRadius * animatedRoundness),
    )

    KepkoTheme(
        palette = palette,
        grayscale = persistence.isGrayscaleEnabled(profileId),
        dimensions = animatedDimensions,
        shapes = animatedShapes,
        content = content,
    )
}
