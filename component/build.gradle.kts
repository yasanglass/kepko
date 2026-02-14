import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val artifactId = "component"

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.roborazzi)
}

kotlin {
    explicitApi()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
        freeCompilerArgs.add("-Xcontext-sensitive-resolution")
        freeCompilerArgs.add("-opt-in=glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi")
    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    js {
        browser()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":foundation"))

                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(compose.ui)
            }
        }

        androidMain {
            dependencies {
                implementation(compose.preview)
            }
        }

        val nonAndroidMain by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependsOn(nonAndroidMain)
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        val jsMain by getting {
            dependsOn(nonAndroidMain)
        }

        val wasmJsMain by getting {
            dependsOn(nonAndroidMain)
        }

        val iosMain by getting {
            dependsOn(nonAndroidMain)
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.composable.preview.scanner.jvm)
                implementation(libs.roborazzi.compose.desktop)
                implementation(kotlin("reflect"))
                implementation(kotlin("test"))
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)
            }
        }
    }
}

android {
    namespace = "glass.yasan.kepko.$artifactId"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "glass.yasan.kepko.$artifactId"
    generateResClass = auto
}

configure<MavenPublishBaseExtension> {
    coordinates(artifactId = artifactId)
}

tasks.register<Delete>("cleanSnapshots") {
    delete(fileTree("src/jvmTest/snapshots") { include("*.png") })
}

tasks.register("cleanRecordSnapshots") {
    description = "Deletes all snapshots and re-records them"
    dependsOn("cleanSnapshots")
    finalizedBy("recordRoborazziJvm")
}

tasks.register("verifySnapshots") {
    description = "Verifies snapshots match baselines"
    dependsOn("verifyRoborazziJvm")
}

val isCleanRecord = providers.gradleProperty("cleanRecordSnapshots").isPresent ||
    gradle.startParameter.taskNames.any { it.contains("cleanRecordSnapshots") }

tasks.named("jvmTest") {
    mustRunAfter("cleanSnapshots")
    if (isCleanRecord) {
        outputs.upToDateWhen { false }
    }
}
