<p align="center">
  <img src="./assets/banner.png" alt="Banner"/>
</p>

<p align="center">
  <a href="https://central.sonatype.com/artifact/glass.yasan.kepko/foundation"><img alt="version" src="https://img.shields.io/maven-central/v/glass.yasan.kepko/foundation?label=version"/></a>
  <a href="LICENSE"><img alt="License" src="https://img.shields.io/github/license/yasanglass/kepko.svg"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3.10-purple.svg?style=flat"/></a>
  <a href="https://crowdin.com/project/kepko"><img alt="Crowdin" src="https://badges.crowdin.net/kepko/localized.svg"/></a>
  <a href="https://github.com/yasanglass/kepko/actions/workflows/android.yml"><img alt="android" src="https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/android.yml?label=android"/></a>
  <a href="https://github.com/yasanglass/kepko/actions/workflows/jvm.yml"><img alt="jvm" src="https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/jvm.yml?label=jvm"/></a>
  <a href="https://github.com/yasanglass/kepko/actions/workflows/ios.yml"><img alt="ios" src="https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/ios.yml?label=ios"/></a>
  <a href="https://github.com/yasanglass/kepko/actions/workflows/js.yml"><img alt="js" src="https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/js.yml?label=js"/></a>
  <a href="https://github.com/yasanglass/kepko/actions/workflows/wasm.yml"><img alt="wasm" src="https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/wasm.yml?label=wasm"/></a>
  <a href="https://github.com/yasanglass/kepko/actions/workflows/detekt.yml"><img alt="detekt" src="https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/detekt.yml?label=detekt"/></a>
  <a href="https://github.com/yasanglass/kepko/actions/workflows/snapshots.yml"><img alt="snapshots" src="https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/snapshots.yml?label=snapshots"/></a>
  <a href="https://github.com/yasanglass/kepko/actions/workflows/publish.yml"><img alt="publish" src="https://img.shields.io/github/actions/workflow/status/yasanglass/kepko/publish.yml?label=publish"/></a>
  <a href="https://context7.com/yasanglass/kepko/llms.txt"><img alt="Context7" src="https://img.shields.io/badge/context7-llms.txt-blue"/></a>
</p>

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

- [Button](component/src/commonMain/kotlin/glass/yasan/kepko/component/Button.kt)
- [ButtonText](component/src/commonMain/kotlin/glass/yasan/kepko/component/ButtonText.kt)
- [Checkbox](component/src/commonMain/kotlin/glass/yasan/kepko/component/Checkbox.kt)
- [CircularProgressIndicator](component/src/commonMain/kotlin/glass/yasan/kepko/component/ProgressIndicator.kt)
- [HorizontalDivider](component/src/commonMain/kotlin/glass/yasan/kepko/component/HorizontalDivider.kt)
- [Icon](component/src/commonMain/kotlin/glass/yasan/kepko/component/Icon.kt)
- [LinearProgressIndicator](component/src/commonMain/kotlin/glass/yasan/kepko/component/ProgressIndicator.kt)
- [PreferenceAppIdentity](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceAppIdentity.kt)
- [PreferenceCheckbox](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceCheckbox.kt)
- [PreferenceContainer](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceContainer.kt)
- [PreferenceRadioButton](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceRadioButton.kt)
- [PreferenceRadioGroup](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceRadioGroup.kt)
- [PreferenceSlider](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceSlider.kt)
- [PreferenceSwitch](component/src/commonMain/kotlin/glass/yasan/kepko/component/PreferenceSwitch.kt)
- [RadioButton](component/src/commonMain/kotlin/glass/yasan/kepko/component/RadioButton.kt)
- [Scaffold](component/src/commonMain/kotlin/glass/yasan/kepko/component/Scaffold.kt)
- [Slider](component/src/commonMain/kotlin/glass/yasan/kepko/component/Slider.kt)
- [Surface](component/src/commonMain/kotlin/glass/yasan/kepko/component/Surface.kt)
- [Switch](component/src/commonMain/kotlin/glass/yasan/kepko/component/Switch.kt)
- [Text](component/src/commonMain/kotlin/glass/yasan/kepko/component/Text.kt)
- [TextMono](component/src/commonMain/kotlin/glass/yasan/kepko/component/TextMono.kt)
- [TextPill](component/src/commonMain/kotlin/glass/yasan/kepko/component/TextPill.kt)
- [VerticalDivider](component/src/commonMain/kotlin/glass/yasan/kepko/component/VerticalDivider.kt)

## Sample

A simple sample app is included so you can explore Kepko in a real project: [sample](sample).

![Sample screenshot](./assets/sample.png)
