package glass.yasan.concrete.sample

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
import glass.yasan.concrete.component.HorizontalDivider
import glass.yasan.concrete.component.Slider
import glass.yasan.concrete.component.Switch
import glass.yasan.concrete.component.Text
import glass.yasan.concrete.component.TextMono
import glass.yasan.concrete.component.border
import glass.yasan.concrete.composeapp.generated.resources.Res
import glass.yasan.concrete.composeapp.generated.resources.app_title
import glass.yasan.concrete.foundation.theme.ConcreteTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SampleApp() {
    val isSystemInDarkTheme = isSystemInDarkTheme()

    val isDarkTheme = rememberSaveable { mutableStateOf(isSystemInDarkTheme) }
    val sliderValue = rememberSaveable { mutableStateOf(0.5f) }

    ConcreteTheme(
        isDark = isDarkTheme.value,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(ConcreteTheme.colors.foreground)
                .safeContentPadding()
                .fillMaxSize()
                .padding(16.dp),
        ) {
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

private data class ColorItem(
    val color: Color,
    val onColor: Color,
    val name: String,
)

@Composable
private fun ColorPaletteContent() {
    ColorsRow(
        listOf(
            ColorItem(ConcreteTheme.colors.content, ConcreteTheme.colors.foreground, "Content"),
            ColorItem(ConcreteTheme.colors.contentSubtle, ConcreteTheme.colors.foreground, "Content Subtle"),
            ColorItem(ConcreteTheme.colors.contentDisabled, ConcreteTheme.colors.foreground, "Content Disabled"),
        )
    )
    ColorsRow(
        listOf(
            ColorItem(ConcreteTheme.colors.foreground, ConcreteTheme.colors.content, "Foreground"),
            ColorItem(ConcreteTheme.colors.midground, ConcreteTheme.colors.content, "Midground"),
            ColorItem(ConcreteTheme.colors.background, ConcreteTheme.colors.content, "Background"),
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
private fun ColorsRow(colors: List<ColorItem>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        colors.forEach { item ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(256.dp)
                    .background(item.color)
                    .border(color = ConcreteTheme.colors.background),
            ) {
                Text(
                    text = item.name,
                    color = item.onColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}

