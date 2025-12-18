package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.resources.stringResource

@Immutable
public class PreferenceAnnotation(
    public val text: @Composable () -> String,
    public val containerColor: @Composable () -> Color,
    public val contentColor: @Composable () -> Color = { contentColorFor(containerColor()) },
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

    }

}

