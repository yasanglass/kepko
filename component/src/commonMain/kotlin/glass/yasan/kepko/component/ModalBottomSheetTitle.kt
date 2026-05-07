package glass.yasan.kepko.component

import androidx.compose.ui.graphics.painter.Painter

public data class ModalBottomSheetTitle(
    public val text: String,
    public val icon: Painter? = null,
    public val description: String? = null,
)
