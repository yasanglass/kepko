import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val artifactId = "serialization"

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

kotlin {
    explicitApi()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
        freeCompilerArgs.add("-Xcontext-sensitive-resolution")
        freeCompilerArgs.add("-opt-in=glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi")
        freeCompilerArgs.add("-opt-in=glass.yasan.kepko.foundation.annotation.InternalKepkoApi")
    }

    android {
        namespace = "glass.yasan.kepko.$artifactId"
        compileSdk = libs.versions.android.sdk.compile.get().toInt()
        minSdk = libs.versions.android.sdk.min.get().toInt()

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }

        androidResources { enable = true }
    }
    jvm()
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
                api(project(":component"))
                api(libs.jetbrains.compose.runtime)
                api(libs.jetbrains.compose.ui)
                api(libs.kotlinx.serialization.json)
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
                implementation(libs.jetbrains.kotlin.test)
            }
        }
    }
}

configure<MavenPublishBaseExtension> {
    coordinates(artifactId = artifactId)
}

