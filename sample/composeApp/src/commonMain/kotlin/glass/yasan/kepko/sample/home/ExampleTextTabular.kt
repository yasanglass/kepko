package glass.yasan.kepko.sample.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.component.HorizontalDivider
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.component.TextTabular
import glass.yasan.kepko.resource.Fonts
import kotlinx.coroutines.delay

@Suppress("LongMethod")
internal fun LazyListScope.exampleTextTabular() {
    item { HorizontalDivider() }
    item {
        var seconds by remember { mutableIntStateOf(0) }
        LaunchedEffect(Unit) {
            while (true) {
                delay(1_000)
                seconds++
            }
        }
        val h24 = (seconds / 3600) % 24
        val m = (seconds / 60) % 60
        val s = seconds % 60
        val time24 = "${h24.toString().padStart(2, '0')}:" +
            "${m.toString().padStart(2, '0')}:" +
            s.toString().padStart(2, '0')

        val h12 = when {
            h24 == 0 -> 12
            h24 > 12 -> h24 - 12
            else -> h24
        }
        val amPm = if (seconds % 7 < 4) "AM" else "PM"
        val time12 = "${h12.toString().padStart(2, '0')}:" +
            "${m.toString().padStart(2, '0')}:" +
            "${s.toString().padStart(2, '0')} $amPm"

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "TextTabular",
                fontWeight = FontWeight.Medium,
            )
            TextTabular(
                text = time24,
                fontSize = 32.sp,
            )
            TextTabular(
                text = time24,
                fontSize = 32.sp,
                fontFamily = Fonts.rubikMonoFontFamily(),
            )
            TextTabular(
                text = time12,
                fontSize = 32.sp,
            )
            TextTabular(
                text = time12,
                fontSize = 32.sp,
                fontFamily = Fonts.rubikMonoFontFamily(),
            )
            TextTabular(
                text = time24,
                fontSize = 32.sp,
                animationSpec = null,
            )
            TextTabular(
                text = time24,
                fontSize = 32.sp,
                fontFamily = Fonts.rubikMonoFontFamily(),
                animationSpec = null,
            )
        }
    }
    item {
        val words = arrayOf("Hello!", "World!", "Kepko!", "Rubik!", "Glass!", "Yasan!")
        var index by remember { mutableIntStateOf(0) }
        LaunchedEffect(Unit) {
            while (true) {
                delay(2_000)
                index = (index + 1) % words.size
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextTabular(
                text = words[index],
                fontSize = 24.sp,
            )
        }
    }
}
