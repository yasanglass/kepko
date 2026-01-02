package glass.yasan.kepko.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource

public object Icons {

    public val chevronBackward: Painter
        @Composable get() = painterResource(Res.drawable.ic_chevron_backward)

    public val chevronForward: Painter
        @Composable get() = painterResource(Res.drawable.ic_chevron_forward)

}
