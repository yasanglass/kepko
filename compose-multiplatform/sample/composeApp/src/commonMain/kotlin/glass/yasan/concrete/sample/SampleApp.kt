package glass.yasan.concrete.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.concrete.component.Switch
import glass.yasan.concrete.component.Text
import glass.yasan.concrete.component.TextMono
import glass.yasan.concrete.component.border
import glass.yasan.concrete.composeapp.generated.resources.Res
import glass.yasan.concrete.composeapp.generated.resources.app_title
import glass.yasan.concrete.foundation.color.isDynamicAccentSupported
import glass.yasan.concrete.foundation.theme.ConcreteTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SampleApp() {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val isDarkTheme = remember { mutableStateOf(isSystemInDarkTheme) }

    val isDynamicAccentSupported = isDynamicAccentSupported()
    val isDynamicAccentAllowed = remember { mutableStateOf(isDynamicAccentSupported) }

    ConcreteTheme(
        isDark = isDarkTheme.value,
        isDynamicAccentAllowed = isDynamicAccentAllowed.value,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(ConcreteTheme.colors.midground)
                .safeContentPadding()
                .fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(ConcreteTheme.colors.foreground)
                    .padding(16.dp),
            ) {
                TextMono(
                    text = stringResource(Res.string.app_title).uppercase(),
                    fontSize = 32.sp,
                )
                Spacer(Modifier.height(12.dp))
                ColorPalette()
                Spacer(Modifier.height(12.dp))
                DarkThemeSwitch(isDarkTheme)
                DynamicAccentColorsSwitch(
                    isDynamicAccentAllowed = isDynamicAccentAllowed,
                    isDynamicAccentSupported = isDynamicAccentSupported,
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.ColorPalette() {
    ColorsRow(
        arrayOf(
            ConcreteTheme.colors.primaryHigh,
            ConcreteTheme.colors.primary,
            ConcreteTheme.colors.primaryLow
        )
    )
    ColorsRow(
        arrayOf(
            ConcreteTheme.colors.secondaryHigh,
            ConcreteTheme.colors.secondary,
            ConcreteTheme.colors.secondaryLow
        )
    )
    ColorsRow(
        arrayOf(
            ConcreteTheme.colors.tertiaryHigh,
            ConcreteTheme.colors.tertiary,
            ConcreteTheme.colors.tertiaryLow
        )
    )
    ColorsRow(
        arrayOf(
            ConcreteTheme.colors.content,
            ConcreteTheme.colors.contentSubtle,
        )
    )
    ColorsRow(
        arrayOf(
            ConcreteTheme.colors.foreground,
            ConcreteTheme.colors.midground,
            ConcreteTheme.colors.background,
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
private fun DynamicAccentColorsSwitch(
    isDynamicAccentAllowed: MutableState<Boolean>,
    isDynamicAccentSupported: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isDynamicAccentSupported) {
                isDynamicAccentAllowed.value = !isDynamicAccentAllowed.value
            }
    ) {
        Text(
            text = "Dynamic Accent Colors",
        )
        Spacer(Modifier.weight(1f))
        Switch(
            checked = isDynamicAccentAllowed.value,
            onCheckedChange = { isDynamicAccentAllowed.value = it },
            enabled = isDynamicAccentSupported,
        )
    }
}

@Composable
private fun ColumnScope.ColorsRow(colors: Array<Color>) {
    Row(
        modifier = Modifier
            .weight(1f),
    ) {
        colors.forEach { color ->
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color)
                    .border(color = ConcreteTheme.colors.background),
            )
        }
    }
}

