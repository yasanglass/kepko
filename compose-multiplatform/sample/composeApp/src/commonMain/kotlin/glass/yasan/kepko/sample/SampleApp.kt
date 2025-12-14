package glass.yasan.kepko.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.component.ButtonText
import glass.yasan.kepko.component.PreferenceAnnotation
import glass.yasan.kepko.component.PreferenceRadioGroup
import glass.yasan.kepko.component.PreferenceSlider
import glass.yasan.kepko.component.PreferenceSwitch
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.TextMono
import glass.yasan.kepko.composeapp.generated.resources.Res
import glass.yasan.kepko.composeapp.generated.resources.app_icon
import glass.yasan.kepko.composeapp.generated.resources.app_title
import glass.yasan.kepko.foundation.border.border
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.resources.painterResource
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

        SystemBarColorsEffect(
            statusBarColor = KepkoTheme.colors.midground,
            navigationBarColor = KepkoTheme.colors.midground,
            isDark = isDarkTheme.value,
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(KepkoTheme.colors.midground)
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
            item {
                PreferenceSlider(
                    title = "Slider",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    value = sliderValue.value,
                    onValueChange = { sliderValue.value = it },
                    steps = 9,
                    annotation = PreferenceAnnotation.new,
                )
            }
            preferenceRadioGroupSample()
            buttonTextSample()
        }
    }
}

fun LazyListScope.preferenceRadioGroupSample() {
    item {
        val items = listOf(
            PreferenceRadioGroupItem { "Item 1" },
            PreferenceRadioGroupItem { "Item 2" },
            PreferenceRadioGroupItem { "Item 3" },
        )
        val selected = remember { mutableStateOf(items.first()) }

        PreferenceRadioGroup(
            title = "Preference Radio Group",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            selected = selected.value,
            items = items,
            onSelect = { selected.value = it },
            annotation = PreferenceAnnotation.new,
        )
    }
}

fun LazyListScope.buttonTextSample() {
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = painterResource(Res.drawable.app_icon),
            trailingIcon = painterResource(Res.drawable.app_icon),
            containerColor = KepkoTheme.colors.foreground,
            onClick = {},
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = painterResource(Res.drawable.app_icon),
            trailingIcon = painterResource(Res.drawable.app_icon),
            onClick = {},
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            containerColor = KepkoTheme.colors.caution,
            leadingIcon = painterResource(Res.drawable.app_icon),
            trailingIcon = painterResource(Res.drawable.app_icon),
            onClick = {},
            fillWidth = false,
        )
    }
}

@Composable
private fun ColorPaletteContent() {
    ColorsRow(
        arrayOf(
            KepkoTheme.colors.success,
            KepkoTheme.colors.information,
            KepkoTheme.colors.caution,
            KepkoTheme.colors.danger,
        )
    )
    ColorsRow(
        arrayOf(
            KepkoTheme.colors.content,
            KepkoTheme.colors.contentSubtle,
            KepkoTheme.colors.contentDisabled,
        )
    )
    ColorsRow(
        arrayOf(
            KepkoTheme.colors.foreground,
            KepkoTheme.colors.midground,
            KepkoTheme.colors.background,
        )
    )
}

@Composable
private fun DarkThemeSwitch(isDarkTheme: MutableState<Boolean>) {
    PreferenceSwitch(
        title = "Dark Theme",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        checked = isDarkTheme.value,
        onCheckedChange = { isDarkTheme.value = it },
        annotation = PreferenceAnnotation.experimental,
    )
}

@Composable
private fun ColorsRow(colorsWithNames: Array<Color>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        colorsWithNames.forEach {
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(256.dp)
                    .background(it)
                    .border(color = KepkoTheme.colors.background),
            )
        }
    }
}

