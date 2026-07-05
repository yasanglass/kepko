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
public data class Badge(
    public val text: @Composable () -> String,
    public val containerColor: @Composable () -> Color = { KepkoTheme.colors.foreground },
    public val contentColor: @Composable () -> Color = { contentColorFor(containerColor()) },
    public val leadingIcon: @Composable (() -> Painter)? = null,
    public val trailingIcon: @Composable (() -> Painter)? = null,
) {

    public companion object {

        public val active: Badge = Badge(
            text = { Strings.preferenceBadgeActive },
            containerColor = { KepkoTheme.colors.information },
        )

        public val new: Badge = Badge(
            text = { Strings.preferenceBadgeNew },
            containerColor = { KepkoTheme.colors.information },
        )

        public fun overriddenBy(profileName: @Composable () -> String): Badge = Badge(
            text = { Strings.preferenceBadgeOverriddenBy(profileName()) },
            containerColor = { KepkoTheme.colors.caution },
        )

        public val default: Badge = Badge(
            text = { Strings.preferenceBadgeDefault },
            containerColor = { KepkoTheme.colors.content },
        )

        public val earlyAccess: Badge = Badge(
            text = { Strings.preferenceBadgeEarlyAccess },
            containerColor = { KepkoTheme.colors.danger },
        )

        public val preview: Badge = Badge(
            text = { Strings.preferenceBadgePreview },
            containerColor = { KepkoTheme.colors.content },
        )

        public val experimental: Badge = Badge(
            text = { Strings.preferenceBadgeExperimental },
            containerColor = { KepkoTheme.colors.danger },
        )

        public val beta: Badge = Badge(
            text = { Strings.preferenceBadgeBeta },
            containerColor = { KepkoTheme.colors.caution },
        )

        public val alpha: Badge = Badge(
            text = { Strings.preferenceBadgeAlpha },
            containerColor = { KepkoTheme.colors.danger },
        )

        public val legacy: Badge = Badge(
            text = { Strings.preferenceBadgeLegacy },
            containerColor = { KepkoTheme.colors.danger },
        )

        public val all: List<Badge> = listOf(
            new,
            default,
            earlyAccess,
            preview,
            experimental,
            beta,
            alpha,
            legacy,
        )

    }

    @ExperimentalKepkoApi
    public fun subtle(): Badge = copy(
        containerColor = { KepkoTheme.colors.foreground },
        contentColor = { KepkoTheme.colors.contentDisabled },
    )

}

