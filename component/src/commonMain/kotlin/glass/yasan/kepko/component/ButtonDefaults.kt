package glass.yasan.kepko.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults as Material3ButtonDefaults

public object ButtonDefaults {
    public val ContentPadding: PaddingValues = Material3ButtonDefaults.ContentPadding

    @Composable
    public fun contentPadding(
        contentPadding: PaddingValues = ContentPadding,
    ): PaddingValues = contentPadding

    @Composable
    public fun buttonElevation(
        defaultElevation: Dp = 0.dp,
        pressedElevation: Dp = 0.dp,
        focusedElevation: Dp = 0.dp,
        hoveredElevation: Dp = 0.dp,
        disabledElevation: Dp = 0.dp,
    ): ButtonElevation = Material3ButtonDefaults.buttonElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        disabledElevation = disabledElevation,
    )
}
