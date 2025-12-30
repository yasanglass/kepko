package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Res
import glass.yasan.kepko.resource.preference_annotation_alpha
import glass.yasan.kepko.resource.preference_annotation_beta
import glass.yasan.kepko.resource.preference_annotation_experimental
import glass.yasan.kepko.resource.preference_annotation_legacy
import glass.yasan.kepko.resource.preference_annotation_new
import glass.yasan.kepko.resource.preference_annotation_preview
import org.jetbrains.compose.resources.stringResource

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
            text = { stringResource(Res.string.preference_annotation_new) },
            containerColor = { KepkoTheme.colors.information },
        )

        public val preview: PreferenceAnnotation = PreferenceAnnotation(
            text = { stringResource(Res.string.preference_annotation_preview) },
            containerColor = { KepkoTheme.colors.content },
        )

        public val experimental: PreferenceAnnotation = PreferenceAnnotation(
            text = { stringResource(Res.string.preference_annotation_experimental) },
            containerColor = { KepkoTheme.colors.danger },
        )

        public val beta: PreferenceAnnotation = PreferenceAnnotation(
            text = { stringResource(Res.string.preference_annotation_beta) },
            containerColor = { KepkoTheme.colors.caution },
        )

        public val alpha: PreferenceAnnotation = PreferenceAnnotation(
            text = { stringResource(Res.string.preference_annotation_alpha) },
            containerColor = { KepkoTheme.colors.danger },
        )

        public val legacy: PreferenceAnnotation = PreferenceAnnotation(
            text = { stringResource(Res.string.preference_annotation_legacy) },
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

