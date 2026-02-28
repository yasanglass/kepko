<p align="center">
  <img src="https://raw.githubusercontent.com/yasanglass/kepko/main/assets/banner.png" alt="Banner"/>
</p>

<p align="center">
  <a href="https://central.sonatype.com/artifact/glass.yasan.kepko/foundation"><img alt="version" src="https://img.shields.io/maven-central/v/glass.yasan.kepko/foundation?label=version"/></a>
  <a href="https://github.com/yasanglass/kepko/blob/main/LICENSE"><img alt="License" src="https://img.shields.io/github/license/yasanglass/kepko.svg"/></a>
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

Wrap your app content with [`KepkoTheme`](https://github.com/yasanglass/kepko/blob/main/foundation/src/commonMain/kotlin/glass/yasan/kepko/foundation/theme/Theme.kt):

| Code | Preview |
|---|---|
| <pre lang="kotlin">KepkoTheme {&#10;    Text("Hello, Kepko!")&#10;    TextPill(&#10;        text = "Yasan Glass",&#10;        containerColor = KepkoTheme.colors.information,&#10;    )&#10;}</pre> | <img src="https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/UsageLightReadmePreview.png" alt="Usage" width="300"/> |

## Components

| Component | Light | Dark |
|---|---|---|
| [ButtonText](https://github.com/yasanglass/kepko/blob/main/component/src/commonMain/kotlin/glass/yasan/kepko/component/ButtonText.kt) | ![ButtonText Light](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/ButtonTextLightReadmePreview.png) | ![ButtonText Dark](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/ButtonTextDarkReadmePreview.png) |
| [OutlinedTextField](https://github.com/yasanglass/kepko/blob/main/component/src/commonMain/kotlin/glass/yasan/kepko/component/OutlinedTextField.kt) | ![OutlinedTextField Light](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/OutlinedTextFieldLightReadmePreview.png) | ![OutlinedTextField Dark](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/OutlinedTextFieldDarkReadmePreview.png) |
| [PreferenceCheckbox](https://github.com/yasanglass/kepko/blob/main/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceCheckbox.kt) | ![PreferenceCheckbox Light](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceCheckboxLightReadmePreview.png) | ![PreferenceCheckbox Dark](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceCheckboxDarkReadmePreview.png) |
| [PreferenceRadioButton](https://github.com/yasanglass/kepko/blob/main/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceRadioButton.kt) | ![PreferenceRadioButton Light](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceRadioButtonLightReadmePreview.png) | ![PreferenceRadioButton Dark](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceRadioButtonDarkReadmePreview.png) |
| [PreferenceRadioGroup](https://github.com/yasanglass/kepko/blob/main/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceRadioGroup.kt) | ![PreferenceRadioGroup Light](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceRadioGroupLightReadmePreview.png) | ![PreferenceRadioGroup Dark](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceRadioGroupDarkReadmePreview.png) |
| [PreferenceSlider](https://github.com/yasanglass/kepko/blob/main/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceSlider.kt) | ![PreferenceSlider Light](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceSliderLightReadmePreview.png) | ![PreferenceSlider Dark](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceSliderDarkReadmePreview.png) |
| [PreferenceSwitch](https://github.com/yasanglass/kepko/blob/main/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceSwitch.kt) | ![PreferenceSwitch Light](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceSwitchLightReadmePreview.png) | ![PreferenceSwitch Dark](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/PreferenceSwitchDarkReadmePreview.png) |

See the full list of components [here](https://github.com/yasanglass/kepko/tree/main/component/src/commonMain/kotlin/glass/yasan/kepko/component).

## Theme Styles

| Theme Style | Preview |
|---|---|
| [Light](https://github.com/yasanglass/kepko/blob/main/foundation/src/commonMain/kotlin/glass/yasan/kepko/foundation/theme/ThemeStyle.kt) | ![Light](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/ThemeStyleLightReadmePreview.png) |
| [Dark](https://github.com/yasanglass/kepko/blob/main/foundation/src/commonMain/kotlin/glass/yasan/kepko/foundation/theme/ThemeStyle.kt) | ![Dark](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/ThemeStyleDarkReadmePreview.png) |
| [Black (AMOLED)](https://github.com/yasanglass/kepko/blob/main/foundation/src/commonMain/kotlin/glass/yasan/kepko/foundation/theme/ThemeStyle.kt) | ![Black (AMOLED)](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/ThemeStyleBlackReadmePreview.png) |
| [Solarized Light](https://github.com/yasanglass/kepko/blob/main/foundation/src/commonMain/kotlin/glass/yasan/kepko/foundation/theme/ThemeStyle.kt) | ![Solarized Light](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/ThemeStyleSolarizedLightReadmePreview.png) |
| [Solarized Dark](https://github.com/yasanglass/kepko/blob/main/foundation/src/commonMain/kotlin/glass/yasan/kepko/foundation/theme/ThemeStyle.kt) | ![Solarized Dark](https://raw.githubusercontent.com/yasanglass/kepko/main/sample/composeApp/assets/readme/ThemeStyleSolarizedDarkReadmePreview.png) |

## Persistence

An optional module that automatically persists and restores theme preferences across the app launches.

```kotlin
implementation("glass.yasan.kepko:persistence:<version>")
```

Use [`PersistentKepkoTheme`](https://github.com/yasanglass/kepko/blob/main/persistence/src/commonMain/kotlin/glass/yasan/kepko/persistence/PersistentKepkoTheme.kt) instead of `KepkoTheme` to automatically persist and restore theme preferences:

```kotlin
@OptIn(ExperimentalKepkoApi::class)
PersistentKepkoTheme {
    // your app content
}
```

To let users change the persisted theme setting, use [`PersistentPreferenceThemeScreen`](https://github.com/yasanglass/kepko/blob/main/persistence/src/commonMain/kotlin/glass/yasan/kepko/persistence/PersistentPreferenceThemeScreen.kt):

```kotlin
PersistentPreferenceThemeScreen(
    onBackClick = { /* navigate back */ },
)
```

[`PersistentKepkoTheme`](https://github.com/yasanglass/kepko/blob/main/persistence/src/commonMain/kotlin/glass/yasan/kepko/persistence/PersistentKepkoTheme.kt) provides the following [`CompositionLocal`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/CompositionLocal) values inside its content:

- [`LocalKepkoThemeStyle`](https://github.com/yasanglass/kepko/blob/main/persistence/src/commonMain/kotlin/glass/yasan/kepko/persistence/PersistenceManager.kt): the currently active [`ThemeStyle`](https://github.com/yasanglass/kepko/blob/main/foundation/src/commonMain/kotlin/glass/yasan/kepko/foundation/theme/ThemeStyle.kt)
- [`LocalKepkoPersistenceManager`](https://github.com/yasanglass/kepko/blob/main/persistence/src/commonMain/kotlin/glass/yasan/kepko/persistence/PersistenceManager.kt): the [`PersistenceManager`](https://github.com/yasanglass/kepko/blob/main/persistence/src/commonMain/kotlin/glass/yasan/kepko/persistence/PersistenceManager.kt) instance for direct access to persistence state

## Sample Project

Explore Kepko in a real project with the included [sample project](https://github.com/yasanglass/kepko/tree/main/sample).

![Sample Light](./sample/composeApp/assets/readme/SampleLightReadmePreview.png) | ![Sample Dark](./sample/composeApp/assets/readme/SampleDarkReadmePreview.png)
---|---
