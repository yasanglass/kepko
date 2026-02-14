package glass.yasan.kepko.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource

public object Icons {

    public val add: Painter
        @Composable get() = painterResource(Res.drawable.ic_add)

    public val bugReport: Painter
        @Composable get() = painterResource(Res.drawable.ic_bug_report)

    public val check: Painter
        @Composable get() = painterResource(Res.drawable.ic_check)

    public val chevronBackward: Painter
        @Composable get() = painterResource(Res.drawable.ic_chevron_backward)

    public val chevronForward: Painter
        @Composable get() = painterResource(Res.drawable.ic_chevron_forward)

    public val close: Painter
        @Composable get() = painterResource(Res.drawable.ic_close)

    public val code: Painter
        @Composable get() = painterResource(Res.drawable.ic_code)

    public val delete: Painter
        @Composable get() = painterResource(Res.drawable.ic_delete)

    public val edit: Painter
        @Composable get() = painterResource(Res.drawable.ic_edit)

    public val error: Painter
        @Composable get() = painterResource(Res.drawable.ic_error)

    public val favorite: Painter
        @Composable get() = painterResource(Res.drawable.ic_favorite)

    public val info: Painter
        @Composable get() = painterResource(Res.drawable.ic_info)

    public val lock: Painter
        @Composable get() = painterResource(Res.drawable.ic_lock)

    public val lockOpen: Painter
        @Composable get() = painterResource(Res.drawable.ic_lock_open)

    public val privacyTip: Painter
        @Composable get() = painterResource(Res.drawable.ic_privacy_tip)

    public val settings: Painter
        @Composable get() = painterResource(Res.drawable.ic_settings)

    public val star: Painter
        @Composable get() = painterResource(Res.drawable.ic_star)

    public val warning: Painter
        @Composable get() = painterResource(Res.drawable.ic_warning)

}
