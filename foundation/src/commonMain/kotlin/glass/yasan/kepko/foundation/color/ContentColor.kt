package glass.yasan.kepko.foundation.color

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import glass.yasan.kepko.foundation.theme.KepkoTheme

@Composable
public fun ProvideLocalContentColor(
    color: Color,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalContentColor provides color.takeOrElse { KepkoTheme.colors.content },
    ) {
        content()
    }
}

@Composable
public fun contentColorFor(
    containerColor: Color,
): Color = when (containerColor) {
    // Layers
    KepkoTheme.colors.foreground -> KepkoTheme.colors.content
    KepkoTheme.colors.midground -> KepkoTheme.colors.content
    KepkoTheme.colors.background -> KepkoTheme.colors.content
    KepkoTheme.colors.outline -> KepkoTheme.colors.content
    // Semantic
    KepkoTheme.colors.success -> KepkoTheme.colors.onSuccess
    KepkoTheme.colors.information -> KepkoTheme.colors.onInformation
    KepkoTheme.colors.caution -> KepkoTheme.colors.onCaution
    KepkoTheme.colors.danger -> KepkoTheme.colors.onDanger
    // Semantic Inverse
    KepkoTheme.colors.onSuccess -> KepkoTheme.colors.success
    KepkoTheme.colors.onInformation -> KepkoTheme.colors.information
    KepkoTheme.colors.onCaution -> KepkoTheme.colors.caution
    KepkoTheme.colors.onDanger -> KepkoTheme.colors.danger
    // Content
    KepkoTheme.colors.content -> KepkoTheme.colors.foreground
    KepkoTheme.colors.contentSubtle -> KepkoTheme.colors.foreground
    KepkoTheme.colors.contentDisabled -> KepkoTheme.colors.foreground
    // Inverse Content
    KepkoTheme.colors.inverseContent -> KepkoTheme.colors.inverseForeground
    KepkoTheme.colors.inverseContentSubtle -> KepkoTheme.colors.inverseForeground
    KepkoTheme.colors.inverseContentDisabled -> KepkoTheme.colors.inverseForeground
    // Inverse Layers
    KepkoTheme.colors.inverseForeground -> KepkoTheme.colors.inverseContent
    KepkoTheme.colors.inverseMidground -> KepkoTheme.colors.inverseContent
    KepkoTheme.colors.inverseBackground -> KepkoTheme.colors.inverseContent
    // Other
    else -> KepkoTheme.colors.content
}
