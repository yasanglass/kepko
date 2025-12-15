![Banner](./assets/banner.png)

[![version](https://img.shields.io/maven-central/v/glass.yasan.kepko/foundation?label=version)](https://central.sonatype.com/artifact/glass.yasan.kepko/core) [![License](https://img.shields.io/github/license/yasanglass/kepko.svg)](LICENSE) [![android](https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/android.yml?label=android)](https://github.com/yasanglass/kepko/actions/workflows/android.yml) [![jvm](https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/jvm.yml?label=jvm)](https://github.com/yasanglass/kepko/actions/workflows/jvm.yml) [![ios](https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/ios.yml?label=ios)](https://github.com/yasanglass/kepko/actions/workflows/ios.yml) [![js](https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/js.yml?label=js)](https://github.com/yasanglass/kepko/actions/workflows/js.yml) [![wasm](https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/wasm.yml?label=wasm)](https://github.com/yasanglass/kepko/actions/workflows/wasm.yml) [![detekt](https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/detekt.yml?label=detekt)](https://github.com/yasanglass/kepko/actions/workflows/detekt.yml) [![publish](https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/publish.yml?label=publish)](https://github.com/yasanglass/kepko/actions/workflows/publish.yml)

A work-in-progress design system for Compose Multiplatform.

## Usage

This library is published on [Maven Central](https://central.sonatype.com/namespace/glass.yasan.kepko):

```kotlin
implementation("glass.yasan.kepko:foundation:<version>")
implementation("glass.yasan.kepko:component:<version>")
```

Wrap your app content with `KepkoTheme`:

```kotlin
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.foundation.theme.KepkoTheme

KepkoTheme {
    Text("Hello, Kepko!")
}
```

## Components

### Primitives
  - [Button](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/Button.kt)
  - [ButtonText](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/ButtonText.kt)
  - [Checkbox](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/Checkbox.kt)
  - [HorizontalDivider](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/HorizontalDivider.kt)
  - [Icon](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/Icon.kt)
  - [Pill](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/Pill.kt)
  - [RadioButton](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/RadioButton.kt)
  - [Slider](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/Slider.kt)
  - [Switch](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/Switch.kt)
  - [Text](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/Text.kt)
  - [TextMono](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/TextMono.kt)
  - [TextPill](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/TextPill.kt)
  - [VerticalDivider](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/VerticalDivider.kt)

### Preferences
  - [PreferenceCheckbox](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceCheckbox.kt)
  - [PreferenceContainer](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceContainer.kt)
  - [PreferenceRadioButton](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceRadioButton.kt)
  - [PreferenceRadioGroup](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceRadioGroup.kt)
  - [PreferenceSlider](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceSlider.kt)
  - [PreferenceSwitch](compose-multiplatform/component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceSwitch.kt)

## Sample

A simple sample app is included so you can explore Kepko in a real project: [compose-multiplatform/sample](compose-multiplatform/sample).

![Sample screenshot](./assets/sample.png)
