package glass.yasan.kepko.sample.readme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.Button
import glass.yasan.kepko.component.OutlinedTextField
import glass.yasan.kepko.component.Badge
import glass.yasan.kepko.component.PreferenceCheckbox
import glass.yasan.kepko.component.PreferenceRadioButton
import glass.yasan.kepko.component.PreferenceRadioGroup
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.PreferenceSlider
import glass.yasan.kepko.component.PreferenceSwitch
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons
import glass.yasan.kepko.sample.ReadmeScreenshotTests.Companion.LOREM_IPSUM
import glass.yasan.kepko.sample.ReadmeScreenshotTests.Companion.previewWidth

@ReadmePreview
@Composable
internal fun ButtonLightReadmePreview() {
    KepkoTheme(palette = defaultLight) {
        ButtonReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ButtonDarkReadmePreview() {
    KepkoTheme(palette = defaultDark) {
        ButtonReadmeContent()
    }
}

@Composable
private fun ButtonReadmeContent() {
    Column(
        modifier = Modifier
            .width(previewWidth)
            .background(KepkoTheme.colors.midground)
            .padding(16.dp),
    ) {
        Button(
            text = "Button",
            onClick = {},
            containerColor = KepkoTheme.colors.foreground,
            leadingIcon = Icons.star,
            trailingIcon = Icons.chevronForward,
            badge = Badge(
                text = { "TextPill" },
                containerColor = { KepkoTheme.colors.caution },
            ),
        )
    }
}

@ReadmePreview
@Composable
internal fun OutlinedTextFieldLightReadmePreview() {
    KepkoTheme(palette = defaultLight) {
        OutlinedTextFieldReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun OutlinedTextFieldDarkReadmePreview() {
    KepkoTheme(palette = defaultDark) {
        OutlinedTextFieldReadmeContent()
    }
}

@Composable
private fun OutlinedTextFieldReadmeContent() {
    Column(
        modifier = Modifier
            .width(previewWidth)
            .background(KepkoTheme.colors.midground)
            .padding(16.dp),
    ) {
        OutlinedTextField(
            value = "OutlinedTextField",
            onValueChange = {},
            label = "Label",
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@ReadmePreview
@Composable
internal fun PreferenceCheckboxLightReadmePreview() {
    KepkoTheme(palette = defaultLight) {
        PreferenceCheckboxReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun PreferenceCheckboxDarkReadmePreview() {
    KepkoTheme(palette = defaultDark) {
        PreferenceCheckboxReadmeContent()
    }
}

@Composable
private fun PreferenceCheckboxReadmeContent() {
    Column(
        modifier = Modifier
            .width(previewWidth)
            .background(KepkoTheme.colors.midground)
            .padding(16.dp),
    ) {
        PreferenceCheckbox(
            title = "PreferenceCheckbox",
            checked = true,
            onCheckedChange = {},
            leadingIcon = Icons.privacyTip,
            description = LOREM_IPSUM,
            badge = Badge(
                text = { "TextPill" },
                containerColor = { KepkoTheme.colors.information },
            ),
        )
    }
}

@ReadmePreview
@Composable
internal fun PreferenceRadioButtonLightReadmePreview() {
    KepkoTheme(palette = defaultLight) {
        PreferenceRadioButtonReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun PreferenceRadioButtonDarkReadmePreview() {
    KepkoTheme(palette = defaultDark) {
        PreferenceRadioButtonReadmeContent()
    }
}

@Composable
private fun PreferenceRadioButtonReadmeContent() {
    Column(
        modifier = Modifier
            .width(previewWidth)
            .background(KepkoTheme.colors.midground)
            .padding(16.dp),
    ) {
        PreferenceRadioButton(
            title = "PreferenceRadioButton",
            selected = true,
            onClick = {},
            leadingIcon = Icons.favorite,
            description = LOREM_IPSUM,
            badge = Badge(
                text = { "TextPill" },
                containerColor = { KepkoTheme.colors.danger },
            ),
        )
    }
}

@ReadmePreview
@Composable
internal fun PreferenceRadioGroupLightReadmePreview() {
    KepkoTheme(palette = defaultLight) {
        PreferenceRadioGroupReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun PreferenceRadioGroupDarkReadmePreview() {
    KepkoTheme(palette = defaultDark) {
        PreferenceRadioGroupReadmeContent()
    }
}

@Composable
private fun PreferenceRadioGroupReadmeContent() {
    val items = listOf(
        PreferenceRadioGroupItem("1") { "Item 1" },
        PreferenceRadioGroupItem("2") { "Item 2" },
        PreferenceRadioGroupItem("3", enabled = false) { "Item 3" },
    )

    Column(
        modifier = Modifier
            .width(previewWidth)
            .background(KepkoTheme.colors.midground)
            .padding(16.dp),
    ) {
        PreferenceRadioGroup(
            title = "PreferenceRadioGroup",
            description = LOREM_IPSUM,
            selected = items.first(),
            items = items,
            onSelect = {},
            badge = Badge(
                text = { "TextPill" },
                containerColor = { KepkoTheme.colors.caution },
            ),
        )
    }
}

@ReadmePreview
@Composable
internal fun PreferenceSliderLightReadmePreview() {
    KepkoTheme(palette = defaultLight) {
        PreferenceSliderReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun PreferenceSliderDarkReadmePreview() {
    KepkoTheme(palette = defaultDark) {
        PreferenceSliderReadmeContent()
    }
}

@Composable
private fun PreferenceSliderReadmeContent() {
    Column(
        modifier = Modifier
            .width(previewWidth)
            .background(KepkoTheme.colors.midground)
            .padding(16.dp),
    ) {
        PreferenceSlider(
            title = "PreferenceSlider",
            description = LOREM_IPSUM,
            value = 0.7f,
            onValueChange = {},
            steps = 9,
            badge = Badge(
                text = { "TextPill" },
                containerColor = { KepkoTheme.colors.success },
            ),
        )
    }
}

@ReadmePreview
@Composable
internal fun PreferenceSwitchLightReadmePreview() {
    KepkoTheme(palette = defaultLight) {
        PreferenceSwitchReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun PreferenceSwitchDarkReadmePreview() {
    KepkoTheme(palette = defaultDark) {
        PreferenceSwitchReadmeContent()
    }
}

@Composable
private fun PreferenceSwitchReadmeContent() {
    Column(
        modifier = Modifier
            .width(previewWidth)
            .background(KepkoTheme.colors.midground)
            .padding(16.dp),
    ) {
        PreferenceSwitch(
            title = "PreferenceSwitch",
            checked = true,
            onCheckedChange = {},
            leadingIcon = Icons.warning,
            description = LOREM_IPSUM,
            badge = Badge(
                text = { "TextPill" },
                containerColor = { KepkoTheme.colors.content },
            ),
        )
    }
}
