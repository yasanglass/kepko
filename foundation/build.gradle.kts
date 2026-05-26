import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val artifactId = "foundation"

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
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
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

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
                api(project(":resource"))

                implementation(libs.jetbrains.compose.components.resources)
                implementation(libs.jetbrains.compose.ui.tooling.preview)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.platformtools.darkmodedetector)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.core)
            }
        }

        val nonAndroidMain by creating {
            dependsOn(commonMain)
        }

        val iosMain by getting {
            dependsOn(nonAndroidMain)
        }

        val nonMobileMain by creating {
            dependsOn(nonAndroidMain)
        }

        val jvmMain by getting {
            dependsOn(nonMobileMain)
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.jetbrains.kotlin.reflect)
                implementation(libs.jetbrains.kotlin.test)
            }
        }

        val jsMain by getting {
            dependsOn(nonMobileMain)
        }

        val wasmJsMain by getting {
            dependsOn(nonMobileMain)
        }

        val macosMain by getting {
            dependsOn(nonMobileMain)
        }
    }
}

dependencies {
    "androidRuntimeClasspath"(libs.jetbrains.compose.ui.tooling)
}

compose.resources {
    publicResClass = false
    packageOfResClass = "glass.yasan.kepko.$artifactId"
    generateResClass = auto
}

configure<MavenPublishBaseExtension> {
    coordinates(artifactId = artifactId)
}
