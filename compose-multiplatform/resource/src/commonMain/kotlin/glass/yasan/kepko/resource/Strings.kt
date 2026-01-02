package glass.yasan.kepko.resource

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource

public object Strings {

    public val back: String
        @Composable get() = stringResource(Res.string.back)

    public val goBack: String
        @Composable get() = stringResource(Res.string.go_back)

    public val preferenceAnnotationAlpha: String
        @Composable get() = stringResource(Res.string.preference_annotation_alpha)

    public val preferenceAnnotationBeta: String
        @Composable get() = stringResource(Res.string.preference_annotation_beta)

    public val preferenceAnnotationExperimental: String
        @Composable get() = stringResource(Res.string.preference_annotation_experimental)

    public val preferenceAnnotationLegacy: String
        @Composable get() = stringResource(Res.string.preference_annotation_legacy)

    public val preferenceAnnotationNew: String
        @Composable get() = stringResource(Res.string.preference_annotation_new)

    public val preferenceAnnotationPreview: String
        @Composable get() = stringResource(Res.string.preference_annotation_preview)

    public val themeStyleBlack: String
        @Composable get() = stringResource(Res.string.theme_style_black)

    public val themeStyleDark: String
        @Composable get() = stringResource(Res.string.theme_style_dark)

    public val themeStyleDarkSolarized: String
        @Composable get() = stringResource(Res.string.theme_style_dark_solarized)

    public val themeStyleLight: String
        @Composable get() = stringResource(Res.string.theme_style_light)

    public val themeStyleLightSolarized: String
        @Composable get() = stringResource(Res.string.theme_style_light_solarized)

}
