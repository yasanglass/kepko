package glass.yasan.kepko.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
public data class PreferenceContainerColors(
    val containerColor: Color,
    val contentColor: Color,
    val descriptionColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val disabledDescriptionColor: Color,
)

@Composable
internal fun PreferenceContainerColors.animated(enabled: Boolean): PreferenceContainerColors {
    val spec = spring<Color>(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessLow,
    )
    return copy(
        containerColor = animateColorAsState(
            targetValue = if (enabled) containerColor else disabledContainerColor,
            animationSpec = spec,
        ).value,
        contentColor = animateColorAsState(
            targetValue = if (enabled) contentColor else disabledContentColor,
            animationSpec = spec,
        ).value,
        descriptionColor = animateColorAsState(
            targetValue = if (enabled) descriptionColor else disabledDescriptionColor,
            animationSpec = spec,
        ).value,
    )
}
