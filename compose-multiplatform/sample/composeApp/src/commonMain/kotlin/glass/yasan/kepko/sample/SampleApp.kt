package glass.yasan.kepko.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.component.HorizontalDivider
import glass.yasan.kepko.component.Slider
import glass.yasan.kepko.component.Switch
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.component.TextMono
import glass.yasan.kepko.component.border
import glass.yasan.kepko.composeapp.generated.resources.Res
import glass.yasan.kepko.composeapp.generated.resources.app_title
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.toolkit.compose.color.toContentColor
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SampleApp() {
    val isSystemInDarkTheme = isSystemInDarkTheme()

    val isDarkTheme = rememberSaveable { mutableStateOf(isSystemInDarkTheme) }
    val sliderValue = rememberSaveable { mutableStateOf(0.5f) }

    KepkoTheme(
        isDark = isDarkTheme.value,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(KepkoTheme.colors.foreground)
                .safeContentPadding()
                .fillMaxSize()
                .padding(16.dp),
        ) {
            item { Spacer(Modifier.height(12.dp)) }
            item {
                TextMono(
                    text = stringResource(Res.string.app_title).uppercase(),
                    fontSize = 32.sp,
                )
            }
            item { Spacer(Modifier.height(12.dp)) }
            item { ColorPaletteContent() }
            item { Spacer(Modifier.height(12.dp)) }
            item { DarkThemeSwitch(isDarkTheme) }
            item { HorizontalDivider() }
            item { Spacer(Modifier.height(12.dp)) }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Slider")
                    Spacer(Modifier.weight(1f))
                    Text(text = sliderValue.value.toString())
                }
            }
            item {
                Slider(
                    value = sliderValue.value,
                    onValueChange = { sliderValue.value = it },
                    steps = 9,
                )
            }
        }
    }
}

@Composable
private fun ColorPaletteContent() {
    ColorsRow(
        arrayOf(
            KepkoTheme.colors.content to "Content",
            KepkoTheme.colors.contentSubtle to "Content Subtle",
            KepkoTheme.colors.contentDisabled to "Content Disabled",
        )
    )
    ColorsRow(
        arrayOf(
            KepkoTheme.colors.foreground to "Foreground",
            KepkoTheme.colors.midground to "Midground",
            KepkoTheme.colors.background to "Background",
        )
    )
}

@Composable
private fun DarkThemeSwitch(isDarkTheme: MutableState<Boolean>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isDarkTheme.value = !isDarkTheme.value
            }
    ) {
        Text(
            text = "Dark Theme",
        )
        Spacer(Modifier.weight(1f))
        Switch(
            checked = isDarkTheme.value,
            onCheckedChange = { isDarkTheme.value = it },
        )
    }
}

@Composable
private fun ColorsRow(colorsWithNames: Array<Pair<Color, String>>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        colorsWithNames.forEach {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(256.dp)
                    .background(it.first)
                    .border(color = KepkoTheme.colors.background),
            ) {
                Text(
                    text = it.second,
                    color = it.first.toContentColor(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}

