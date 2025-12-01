package glass.yasan.concrete.foundation.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
public class Colors(
    public var theme: Theme,
    public var content: Content,
    public var layer: Layer,
    public var isLight: Boolean,
) {

    @Suppress("LongParameterList")
    public constructor(
        themePrimary: Color,
        themeSecondary: Color,
        contentMajor: Color,
        contentMinor: Color,
        layerForeground: Color,
        layerMidground: Color,
        layerBackground: Color,
        layerForegroundInverse: Color,
        layerMidgroundInverse: Color,
        layerBackgroundInverse: Color,
        isLight: Boolean,
    ) : this(
        theme = Theme(
            primary = themePrimary,
            secondary = themeSecondary,
        ),
        content = Content(
            major = contentMajor,
            minor = contentMinor,
        ),
        layer = Layer(
            foreground = layerForeground,
            midground = layerMidground,
            background = layerBackground,
            foregroundInverse = layerForegroundInverse,
            midgroundInverse = layerMidgroundInverse,
            backgroundInverse = layerBackgroundInverse,
        ),
        isLight = isLight,
    )

    public class Content(
        public val major: Color,
        public val minor: Color,
    )

    public class Layer(
        public val foreground: Color,
        public val foregroundInverse: Color,
        public val midground: Color,
        public val midgroundInverse: Color,
        public val background: Color,
        public val backgroundInverse: Color,
    )

    public class Theme(
        public val primary: Color,
        public val secondary: Color,
        public val containerAlpha: Float = 0.8f,
        public val primaryContainer: Color = primary.copy(alpha = containerAlpha),
        public val secondaryContainer: Color = secondary.copy(alpha = containerAlpha),
    )

    public fun copy(
        theme: Theme = this.theme,
        content: Content = this.content,
        layer: Layer = this.layer,
        isLight: Boolean = this.isLight,
    ): Colors = Colors(
        theme = theme,
        content = content,
        layer = layer,
        isLight = isLight,
    )

    public fun updateColorsFrom(
        other: Colors,
    ) {
        theme = other.theme
        content = other.content
        layer = other.layer
        isLight = other.isLight
    }

    @Composable
    internal fun toMaterial3Colors(): ColorScheme =
        MaterialTheme.colorScheme.copy(
            background = layer.midground,
            error = Color.Unspecified,
            errorContainer = Color.Unspecified,
            inverseOnSurface = layer.foreground,
            inversePrimary = contentColor(theme.primary),
            inverseSurface = layer.foregroundInverse,
            onBackground = content.minor,
            onError = Color.Unspecified,
            onErrorContainer = Color.Unspecified,
            onPrimary = contentColor(theme.primary),
            onPrimaryContainer = containerColor(contentColor(theme.primaryContainer)),
            onSecondary = contentColor(theme.secondary),
            onSecondaryContainer = containerColor(contentColor(theme.secondaryContainer)),
            onSurface = content.minor,
            onSurfaceVariant = content.minor,
            onTertiary = Color.Unspecified,
            onTertiaryContainer = Color.Unspecified,
            outline = content.minor,
            outlineVariant = content.minor,
            primary = theme.primary,
            primaryContainer = containerColor(theme.primary),
            scrim = layer.foreground,
            secondary = theme.secondary,
            secondaryContainer = containerColor(theme.secondary),
            surface = layer.foreground,
            surfaceBright = layer.foreground,
            surfaceContainer = layer.foreground,
            surfaceContainerHigh = layer.foreground,
            surfaceContainerHighest = layer.foreground,
            surfaceContainerLow = layer.foreground,
            surfaceContainerLowest = layer.foreground,
            surfaceDim = layer.foreground,
            surfaceTint = layer.foreground,
            surfaceVariant = layer.foreground,
            tertiary = Color.Unspecified,
            tertiaryContainer = Color.Unspecified,
        )

    private fun containerColor(
        color: Color,
        containerAlpha: Float = 0.8f,
    ) = color.copy(alpha = containerAlpha)
}

internal val LocalColors: ProvidableCompositionLocal<Colors> =
    compositionLocalOf {
        lightColors()
    }
