package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Strings

@Immutable
public data class PreferenceAnnotation(
    public val text: @Composable () -> String,
    public val containerColor: @Composable () -> Color = { KepkoTheme.colors.foreground },
    public val contentColor: @Composable () -> Color = { contentColorFor(containerColor()) },
    public val leadingIcon: @Composable (() -> Painter)? = null,
    public val trailingIcon: @Composable (() -> Painter)? = null,
) {

    public companion object {

        public val new: PreferenceAnnotation = PreferenceAnnotation(
            text = { Strings.preferenceAnnotationNew },
            containerColor = { KepkoTheme.colors.information },
        )

        public val default: PreferenceAnnotation = PreferenceAnnotation(
            text = { Strings.preferenceAnnotationDefault },
            containerColor = { KepkoTheme.colors.content },
        )

        public val preview: PreferenceAnnotation = PreferenceAnnotation(
            text = { Strings.preferenceAnnotationPreview },
            containerColor = { KepkoTheme.colors.content },
        )

        public val experimental: PreferenceAnnotation = PreferenceAnnotation(
            text = { Strings.preferenceAnnotationExperimental },
            containerColor = { KepkoTheme.colors.danger },
        )

        public val beta: PreferenceAnnotation = PreferenceAnnotation(
            text = { Strings.preferenceAnnotationBeta },
            containerColor = { KepkoTheme.colors.caution },
        )

        public val alpha: PreferenceAnnotation = PreferenceAnnotation(
            text = { Strings.preferenceAnnotationAlpha },
            containerColor = { KepkoTheme.colors.danger },
        )

        public val legacy: PreferenceAnnotation = PreferenceAnnotation(
            text = { Strings.preferenceAnnotationLegacy },
            containerColor = { KepkoTheme.colors.danger },
        )

        public val all: List<PreferenceAnnotation> = listOf(
            new,
            preview,
            experimental,
            beta,
            alpha,
            legacy,
        )

    }

    @ExperimentalKepkoApi
    public fun subtle(): PreferenceAnnotation = copy(
        containerColor = { KepkoTheme.colors.foreground },
    )

}

