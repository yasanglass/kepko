import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.Executable
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-sensitive-resolution")
        freeCompilerArgs.add("-opt-in=glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi")
    }

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        binaries.executable()
    }

    js {
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    listOf(
        macosX64(),
        macosArm64(),
    ).forEach { macosTarget ->
        macosTarget.binaries.executable {
            entryPoint = "glass.yasan.kepko.sample.main"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":persistence"))

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.navigation.compose)
                implementation(libs.platformtools.darkmodedetector)
            }
        }
        jvmMain {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        androidMain {
            dependencies {
                implementation(compose.preview)
            }
        }
    }
}

android {
    namespace = "glass.yasan.kepko.sample.shared"
    compileSdk = libs.versions.sample.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.sample.android.sdk.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

val macosTargets = kotlin.targets.filterIsInstance<KotlinNativeTarget>().filter { it.name.startsWith("macos") }
for (target in macosTargets) {
    for (executable in target.binaries.filterIsInstance<Executable>()) {
        val taskName = "copyComposeResources" +
            executable.name.replaceFirstChar { it.uppercaseChar() } +
            target.name.replaceFirstChar { it.uppercaseChar() }
        val copyResources = tasks.register<Copy>(taskName) {
            from(tasks.named("${target.name}ProcessResources"))
            into(executable.outputDirectory.resolve("compose-resources"))
        }
        executable.linkTaskProvider.configure { dependsOn(copyResources) }
    }
}

compose.desktop {
    application {
        mainClass = "glass.yasan.kepko.sample.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Kepko"
            packageVersion = "1.0.0"
            macOS {
                iconFile.set(project.file("src/jvmMain/resources/app_icon.icns"))
                bundleID = "glass.yasan.kepko.sample"
                dockName = "Kepko"
            }
            windows {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/app_icon.png"))
            }
            linux {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/app_icon.png"))
            }
        }
    }
}
