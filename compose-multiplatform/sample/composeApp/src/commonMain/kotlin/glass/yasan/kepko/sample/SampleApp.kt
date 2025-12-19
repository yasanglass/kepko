package glass.yasan.kepko.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.component.ButtonText
import glass.yasan.kepko.component.HorizontalDivider
import glass.yasan.kepko.component.PreferenceAnnotation
import glass.yasan.kepko.component.PreferenceCheckbox
import glass.yasan.kepko.component.PreferenceRadioButton
import glass.yasan.kepko.component.PreferenceRadioGroup
import glass.yasan.kepko.component.PreferenceSlider
import glass.yasan.kepko.component.PreferenceSwitch
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.component.TextMono
import glass.yasan.kepko.component.TextPill
import glass.yasan.kepko.composeapp.generated.resources.Res
import glass.yasan.kepko.composeapp.generated.resources.app_title
import glass.yasan.kepko.composeapp.generated.resources.ic_arrow_forward
import glass.yasan.kepko.composeapp.generated.resources.ic_bolt
import glass.yasan.kepko.composeapp.generated.resources.ic_eco
import glass.yasan.kepko.composeapp.generated.resources.ic_family_star
import glass.yasan.kepko.composeapp.generated.resources.ic_heart_smile
import glass.yasan.kepko.composeapp.generated.resources.ic_new_releases
import glass.yasan.kepko.foundation.border.border
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.util.asPreferenceRadioGroupItems
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SampleApp() {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val style = rememberSaveable {
        mutableStateOf(ThemeStyle.fromDarkMode(isDark = isSystemInDarkTheme))
    }

    KepkoTheme(
        style = style.value,
    ) {

        SystemBarColorsEffect(
            statusBarColor = KepkoTheme.colors.midground,
            navigationBarColor = KepkoTheme.colors.midground,
            isDark = style.value.isDark,
        )

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .background(KepkoTheme.colors.midground)
                .fillMaxSize()
                .safeContentPadding(),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .widthIn(max = 512.dp),
            ) {
                title()
                colorPalette()
                stylePreference(style)
                examplePreferenceSlider()
                examplePreferenceCheckbox()
                examplePreferenceSwitch()
                examplePreferenceRadioButton()
                examplePreferenceRadioGroup()
                exampleButtonText()
                exampleTextPill()
                item { Spacer(Modifier.height(128.dp)) }
            }
        }
    }
}

private fun LazyListScope.title() {
    item {
        TextMono(
            text = stringResource(Res.string.app_title).uppercase(),
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp),
        )
    }
}

private fun LazyListScope.colorPalette() {
    item {
        Column {
            Row {
                ColorBox(
                    containerColor = KepkoTheme.colors.success,
                    text = "Success",
                )
                ColorBox(
                    containerColor = KepkoTheme.colors.information,
                    text = "Information",
                )
            }
            Row {
                ColorBox(
                    containerColor = KepkoTheme.colors.caution,
                    text = "Caution",
                )
                ColorBox(
                    containerColor = KepkoTheme.colors.danger,
                    text = "Danger",
                )
            }
            Row {
                ColorBox(
                    containerColor = KepkoTheme.colors.foreground,
                    text = "Foreground",
                )
                ColorBox(
                    containerColor = KepkoTheme.colors.midground,
                    text = "Midground",
                )
                ColorBox(
                    containerColor = KepkoTheme.colors.background,
                    text = "Background",
                )
            }
        }
    }
}

private fun LazyListScope.examplePreferenceRadioButton() {
    item {
        HorizontalDivider()
    }
    item {
        val radioButtonValue = rememberSaveable { mutableStateOf(true) }

        PreferenceRadioButton(
            title = "PreferenceRadioButton",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            selected = radioButtonValue.value,
            onClick = { radioButtonValue.value = radioButtonValue.value.not() },
            leadingIcon = painterResource(Res.drawable.ic_heart_smile),
            annotation = PreferenceAnnotation.new,
        )
    }
    item {
        val radioButtonValue = rememberSaveable { mutableStateOf(false) }

        PreferenceRadioButton(
            title = "PreferenceRadioButton",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            selected = radioButtonValue.value,
            onClick = { radioButtonValue.value = radioButtonValue.value.not() },
            leadingIcon = painterResource(Res.drawable.ic_family_star),
        )
    }
    item {
        val radioButtonValue = rememberSaveable { mutableStateOf(true) }

        PreferenceRadioButton(
            title = "PreferenceRadioButton",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            selected = radioButtonValue.value,
            onClick = { radioButtonValue.value = radioButtonValue.value.not() },
        )
    }
    item {
        val radioButtonValue = rememberSaveable { mutableStateOf(false) }

        PreferenceRadioButton(
            title = "PreferenceRadioButton",
            selected = radioButtonValue.value,
            onClick = { radioButtonValue.value = radioButtonValue.value.not() },
        )
    }
    item {
        PreferenceRadioButton(
            title = "PreferenceRadioButton",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            selected = true,
            onClick = {},
            enabled = false,
        )
    }
}

private fun LazyListScope.examplePreferenceSwitch() {
    item {
        HorizontalDivider()
    }
    item {
        val switchValue = rememberSaveable { mutableStateOf(true) }

        PreferenceSwitch(
            title = "PreferenceSwitch",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            checked = switchValue.value,
            onCheckedChange = { switchValue.value = it },
            annotation = PreferenceAnnotation.preview,
            leadingIcon = painterResource(Res.drawable.ic_bolt)
        )
    }
    item {
        val switchValue = rememberSaveable { mutableStateOf(false) }

        PreferenceSwitch(
            title = "PreferenceSwitch",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            checked = switchValue.value,
            onCheckedChange = { switchValue.value = it },
            annotation = PreferenceAnnotation.alpha,
        )
    }
    item {
        val switchValue = rememberSaveable { mutableStateOf(true) }

        PreferenceSwitch(
            title = "PreferenceSwitch",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            checked = switchValue.value,
            onCheckedChange = { switchValue.value = it },
        )
    }
    item {
        val switchValue = rememberSaveable { mutableStateOf(false) }

        PreferenceSwitch(
            title = "PreferenceSwitch",
            checked = switchValue.value,
            onCheckedChange = { switchValue.value = it },
        )
    }
    item {
        PreferenceSwitch(
            title = "PreferenceSwitch",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            checked = true,
            onCheckedChange = {},
            enabled = false,
        )
    }
}


private fun LazyListScope.examplePreferenceCheckbox() {
    item {
        HorizontalDivider()
    }
    item {
        val checkboxValue = rememberSaveable { mutableStateOf(false) }

        PreferenceCheckbox(
            title = "PreferenceCheckbox",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            checked = checkboxValue.value,
            onCheckedChange = { checkboxValue.value = it },
            annotation = PreferenceAnnotation.beta,
        )
    }
    item {
        val checkboxValue = rememberSaveable { mutableStateOf(false) }

        PreferenceCheckbox(
            title = "PreferenceCheckbox",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            checked = checkboxValue.value,
            onCheckedChange = { checkboxValue.value = it },
        )
    }
    item {
        val checkboxValue = rememberSaveable { mutableStateOf(true) }

        PreferenceCheckbox(
            title = "PreferenceCheckbox",
            checked = checkboxValue.value,
            onCheckedChange = { checkboxValue.value = it },
        )
    }
    item {
        PreferenceCheckbox(
            title = "PreferenceCheckbox",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            checked = true,
            onCheckedChange = {},
            enabled = false,
        )
    }
}

private fun LazyListScope.examplePreferenceSlider() {
    item { HorizontalDivider() }
    item {
        val sliderValue = rememberSaveable { mutableStateOf(0.7f) }

        PreferenceSlider(
            title = "PreferenceSlider",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            value = sliderValue.value,
            onValueChange = { sliderValue.value = it },
            steps = 9,
            annotation = PreferenceAnnotation.legacy,
        )
    }
    item {
        val sliderValue = rememberSaveable { mutableStateOf(0.5f) }

        PreferenceSlider(
            title = "PreferenceSlider",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            value = sliderValue.value,
            onValueChange = { sliderValue.value = it },
            steps = 5,
        )
    }
    item {
        val sliderValue = rememberSaveable { mutableStateOf(0.3f) }

        PreferenceSlider(
            title = "PreferenceSlider",
            value = sliderValue.value,
            onValueChange = { sliderValue.value = it },
            steps = 3,
        )
    }
    item {
        PreferenceSlider(
            title = "PreferenceSlider",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            value = 0.5f,
            onValueChange = {},
            steps = 5,
            enabled = false,
        )
    }
}

@Suppress("LongMethod")
private fun LazyListScope.examplePreferenceRadioGroup() {
    item { HorizontalDivider() }
    item {
        val items = listOf(
            PreferenceRadioGroupItem("item1") { "Item 1" },
        )
        val selected = remember { mutableStateOf(items.first()) }

        PreferenceRadioGroup(
            title = "PreferenceRadioGroup",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            selected = selected.value,
            items = items,
            onSelect = { selected.value = it },
            annotation = PreferenceAnnotation.alpha,
        )
    }
    item {
        val items = listOf(
            PreferenceRadioGroupItem("item1") { "Item 1" },
            PreferenceRadioGroupItem("item2") { "Item 2" },
            PreferenceRadioGroupItem("item3", annotation = PreferenceAnnotation.new) { "Item 3" },
            PreferenceRadioGroupItem("item4") { "Item 4" },
            PreferenceRadioGroupItem("item5", annotation = PreferenceAnnotation.experimental) { "Item 5" },
        )
        val selected = remember { mutableStateOf(items.first()) }

        PreferenceRadioGroup(
            title = "PreferenceRadioGroup",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            selected = selected.value,
            items = items,
            onSelect = { selected.value = it },
        )
    }
    item {
        val items = listOf(
            PreferenceRadioGroupItem("item1") { "Item 1" },
            PreferenceRadioGroupItem("item2") { "Item 2" },
        )
        val selected = remember { mutableStateOf(items.first()) }

        PreferenceRadioGroup(
            title = "PreferenceRadioGroup",
            selected = selected.value,
            items = items,
            onSelect = { selected.value = it },
        )
    }
    item {
        val items = listOf(
            PreferenceRadioGroupItem("item1") { "Item 1" },
            PreferenceRadioGroupItem("item2") { "Item 2" },
        )

        PreferenceRadioGroup(
            title = "PreferenceRadioGroup",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            selected = items.first(),
            items = items,
            onSelect = {},
            enabled = false,
        )
    }
}

@Suppress("LongMethod")
private fun LazyListScope.exampleButtonText() {
    item { HorizontalDivider() }
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = painterResource(Res.drawable.ic_bolt),
            trailingIcon = painterResource(Res.drawable.ic_arrow_forward),
            onClick = {},
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = null,
            trailingIcon = painterResource(Res.drawable.ic_new_releases),
            containerColor = KepkoTheme.colors.information,
            onClick = {},
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = painterResource(Res.drawable.ic_family_star),
            trailingIcon = null,
            containerColor = KepkoTheme.colors.caution,
            onClick = {},
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = painterResource(Res.drawable.ic_heart_smile),
            trailingIcon = null,
            containerColor = KepkoTheme.colors.danger,
            onClick = {},
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            containerColor = KepkoTheme.colors.foreground,
            onClick = {},
            textAlign = TextAlign.Center,
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = painterResource(Res.drawable.ic_new_releases),
            trailingIcon = painterResource(Res.drawable.ic_family_star),
            containerColor = KepkoTheme.colors.foreground,
            onClick = {},
            fillWidth = false,
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = painterResource(Res.drawable.ic_heart_smile),
            trailingIcon = null,
            containerColor = KepkoTheme.colors.foreground,
            onClick = {},
            fillWidth = false,
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = null,
            trailingIcon = painterResource(Res.drawable.ic_eco),
            containerColor = KepkoTheme.colors.success,
            onClick = {},
            fillWidth = false,
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            containerColor = KepkoTheme.colors.foreground,
            onClick = {},
            fillWidth = false,
        )
    }
    item {
        ButtonText(
            text = "ButtonText",
            leadingIcon = painterResource(Res.drawable.ic_bolt),
            trailingIcon = painterResource(Res.drawable.ic_arrow_forward),
            onClick = {},
            enabled = false,
        )
    }
}

private fun LazyListScope.exampleTextPill() {
    item { HorizontalDivider() }
    item {
        KepkoTheme.colors.getSemanticColors().forEach { color ->
            TextPill(
                text = "TextPill",
                containerColor = color,
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }
}

private fun LazyListScope.stylePreference(style: MutableState<ThemeStyle>) {
    item {
        PreferenceRadioGroup(
            title = "Style",
            items = ThemeStyle.asPreferenceRadioGroupItems(),
            selectedId = style.value.id,
            onSelectId = { style.value = ThemeStyle.fromId(it) ?: ThemeStyle.LIGHT },
        )
    }
}

@Composable
private fun RowScope.ColorBox(
    containerColor: Color,
    contentColor: Color = contentColorFor(containerColor),
    text: String = "Kepko",
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(shape = CircleShape)
            .clip(CircleShape)
            .weight(1f)
            .height(128.dp)
            .background(containerColor),
    ) {
        Text(
            text = text.uppercase(),
            color = contentColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

