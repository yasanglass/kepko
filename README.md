<p align="center">
  <img src="https://raw.githubusercontent.com/yasanglass/kepko/main/assets/banner.png" alt="Banner"/>
</p>

<p align="center">
  <a href="https://central.sonatype.com/artifact/glass.yasan.kepko/foundation"><img alt="version" src="https://img.shields.io/maven-central/v/glass.yasan.kepko/foundation?label=version"/></a>
  <a href="LICENSE"><img alt="License" src="https://img.shields.io/github/license/yasanglass/kepko.svg"/></a>
  <a href="https://crowdin.com/project/kepko"><img alt="Crowdin" src="https://badges.crowdin.net/kepko/localized.svg"/></a>
  <a href="https://codecov.io/gh/yasanglass/kepko"><img alt="Codecov" src="https://codecov.io/gh/yasanglass/kepko/graph/badge.svg"/></a>
  <a href="https://context7.com/yasanglass/kepko/llms.txt"><img alt="Context7" src="https://img.shields.io/badge/context7-llms.txt-blue"/></a>
  <a href="https://yasanglass.github.io/kepko/"><img alt="Documentation" src="https://img.shields.io/badge/documentation-blue"/></a>
</p>

An opinionated design system for Compose Multiplatform.

## Installation

Published on [Maven Central](https://central.sonatype.com/namespace/glass.yasan.kepko):

```kotlin
implementation("glass.yasan.kepko:component:<version>")
implementation("glass.yasan.kepko:foundation:<version>")
```

## Usage

Wrap your app content with `KepkoTheme`:

| Code | Preview |
|---|---|
| <pre lang="kotlin">KepkoTheme {&#10;    Text("Hello, Kepko!")&#10;    TextPill(&#10;        text = "Yasan Glass",&#10;        containerColor = KepkoTheme.colors.information,&#10;    )&#10;}</pre> | ![Usage](sample/composeApp/assets/readme/UsageLightReadmePreview.png) |

## Components

| Component | Light | Dark |
|---|---|---|
| [ButtonText](component/src/commonMain/kotlin/glass/yasan/kepko/component/ButtonText.kt) | ![ButtonText Light](sample/composeApp/assets/readme/ButtonTextLightReadmePreview.png) | ![ButtonText Dark](sample/composeApp/assets/readme/ButtonTextDarkReadmePreview.png) |
| [OutlinedTextField](component/src/commonMain/kotlin/glass/yasan/kepko/component/OutlinedTextField.kt) | ![OutlinedTextField Light](sample/composeApp/assets/readme/OutlinedTextFieldLightReadmePreview.png) | ![OutlinedTextField Dark](sample/composeApp/assets/readme/OutlinedTextFieldDarkReadmePreview.png) |
| [PreferenceCheckbox](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceCheckbox.kt) | ![PreferenceCheckbox Light](sample/composeApp/assets/readme/PreferenceCheckboxLightReadmePreview.png) | ![PreferenceCheckbox Dark](sample/composeApp/assets/readme/PreferenceCheckboxDarkReadmePreview.png) |
| [PreferenceRadioButton](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceRadioButton.kt) | ![PreferenceRadioButton Light](sample/composeApp/assets/readme/PreferenceRadioButtonLightReadmePreview.png) | ![PreferenceRadioButton Dark](sample/composeApp/assets/readme/PreferenceRadioButtonDarkReadmePreview.png) |
| [PreferenceRadioGroup](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceRadioGroup.kt) | ![PreferenceRadioGroup Light](sample/composeApp/assets/readme/PreferenceRadioGroupLightReadmePreview.png) | ![PreferenceRadioGroup Dark](sample/composeApp/assets/readme/PreferenceRadioGroupDarkReadmePreview.png) |
| [PreferenceSlider](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceSlider.kt) | ![PreferenceSlider Light](sample/composeApp/assets/readme/PreferenceSliderLightReadmePreview.png) | ![PreferenceSlider Dark](sample/composeApp/assets/readme/PreferenceSliderDarkReadmePreview.png) |
| [PreferenceSwitch](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceSwitch.kt) | ![PreferenceSwitch Light](sample/composeApp/assets/readme/PreferenceSwitchLightReadmePreview.png) | ![PreferenceSwitch Dark](sample/composeApp/assets/readme/PreferenceSwitchDarkReadmePreview.png) |

See the full list of components [here](component/src/commonMain/kotlin/glass/yasan/kepko/component).

## Sample Project

Explore Kepko in a real project with the included [sample project](sample).

![Sample Light](sample/composeApp/assets/readme/SampleLightReadmePreview.png) | ![Sample Dark](sample/composeApp/assets/readme/SampleDarkReadmePreview.png)
---|---
