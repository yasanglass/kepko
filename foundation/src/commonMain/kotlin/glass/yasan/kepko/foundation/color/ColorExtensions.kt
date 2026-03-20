package glass.yasan.kepko.foundation.color

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

public const val DEFAULT_LUMINANCE_THRESHOLD: Float = 0.5f
public const val DEFAULT_LUMINANCE_ADJUSTMENT_FACTOR: Float = 0.075f

public fun Color.isDark(
    luminanceThreshold: Float = DEFAULT_LUMINANCE_THRESHOLD,
): Boolean = !isLight(luminanceThreshold)

public fun Color.isLight(
    luminanceThreshold: Float = DEFAULT_LUMINANCE_THRESHOLD,
): Boolean = luminance() > luminanceThreshold

public fun Color.toGrayscale(): Color {
    val luminance = 0.2126f * red + 0.7152f * green + 0.0722f * blue

    return Color(luminance, luminance, luminance, alpha)
}

public fun Color.darken(factor: Float = DEFAULT_LUMINANCE_ADJUSTMENT_FACTOR): Color {
    val hsl = toHsl()
    hsl[2] = (hsl[2] - factor).coerceIn(0f, 1f)
    return hslToColor(hsl, alpha)
}

public fun Color.lighten(factor: Float = DEFAULT_LUMINANCE_ADJUSTMENT_FACTOR): Color {
    val hsl = toHsl()
    hsl[2] = (hsl[2] + factor).coerceIn(0f, 1f)
    return hslToColor(hsl, alpha)
}

private fun Color.toHsl(): FloatArray {
    val r = red
    val g = green
    val b = blue

    val max = maxOf(r, g, b)
    val min = minOf(r, g, b)
    val l = (max + min) / 2f

    val h: Float
    val s: Float

    if (max == min) {
        h = 0f
        s = 0f
    } else {
        val d = max - min
        s = if (l > 0.5f) d / (2f - max - min) else d / (max + min)
        h = when (max) {
            r -> (g - b) / d + (if (g < b) 6f else 0f)
            g -> (b - r) / d + 2f
            else -> (r - g) / d + 4f
        } / 6f
    }

    return floatArrayOf(h * 360f, s, l)
}

private fun hslToColor(hsl: FloatArray, alpha: Float = 1f): Color {
    val h = hsl[0] / 360f
    val s = hsl[1]
    val l = hsl[2]

    val r: Float
    val g: Float
    val b: Float

    if (s == 0f) {
        r = l; g = l; b = l
    } else {
        val q = if (l < 0.5f) l * (1 + s) else l + s - l * s
        val p = 2 * l - q
        r = hueToRgb(p, q, h + 1f / 3f)
        g = hueToRgb(p, q, h)
        b = hueToRgb(p, q, h - 1f / 3f)
    }

    return Color(r, g, b, alpha)
}

private fun hueToRgb(p: Float, q: Float, t: Float): Float {
    var tt = t
    if (tt < 0f) tt += 1f
    if (tt > 1f) tt -= 1f
    return when {
        tt < 1f / 6f -> p + (q - p) * 6f * tt
        tt < 1f / 2f -> q
        tt < 2f / 3f -> p + (q - p) * (2f / 3f - tt) * 6f
        else -> p
    }
}
