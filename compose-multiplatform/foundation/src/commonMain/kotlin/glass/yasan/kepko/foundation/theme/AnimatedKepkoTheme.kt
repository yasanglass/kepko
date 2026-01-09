package glass.yasan.kepko.foundation.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.color.ColorTokens
import kotlin.math.PI
import kotlin.math.sin

@ExperimentalKepkoApi
@Composable
public fun AnimatedKepkoTheme(
    style: ThemeStyle,
    content: @Composable () -> Unit,
) {
    var previousStyle by remember { mutableStateOf(style) }
    var currentStyle by remember { mutableStateOf(style) }
    val revealProgress = remember { Animatable(1f) }

    LaunchedEffect(style) {
        if (style != currentStyle) {
            previousStyle = currentStyle
            currentStyle = style
            revealProgress.snapTo(0f)
            revealProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 700,
                    easing = FastOutSlowInEasing,
                ),
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (revealProgress.value < 1f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ColorTokens.midground(previousStyle)),
            ) {
                KepkoTheme(style = previousStyle) {
                    content()
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(WavyRevealShape(revealProgress.value))
                .background(ColorTokens.midground(currentStyle)),
        ) {
            KepkoTheme(style = currentStyle) {
                content()
            }
        }
    }
}

private class WavyRevealShape(
    private val progress: Float,
    private val waveCount: Int = 3,
    private val waveHeight: Float = 60f,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path()

        val baseY = size.height * progress

        path.moveTo(0f, 0f)
        path.lineTo(size.width, 0f)
        path.lineTo(size.width, baseY)

        val steps = 100
        for (i in steps downTo 0) {
            val x = size.width * (i / steps.toFloat())
            val waveOffset = sin((i / steps.toFloat()) * waveCount * 2 * PI + progress * PI * 2).toFloat()
            val waveAmplitude = waveHeight * (1f - progress).coerceIn(0f, 1f)
            val y = baseY + waveOffset * waveAmplitude
            path.lineTo(x, y.coerceAtLeast(0f))
        }

        path.close()

        return Outline.Generic(path)
    }
}
