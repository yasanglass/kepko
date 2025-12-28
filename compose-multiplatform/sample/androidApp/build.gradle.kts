import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

android {
    namespace = "glass.yasan.kepko.sample.android"
    compileSdk = libs.versions.sample.android.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "glass.yasan.kepko.sample"
        minSdk = libs.versions.sample.android.sdk.min.get().toInt()
        targetSdk = libs.versions.sample.android.sdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":sample:composeApp"))

    implementation(libs.androidx.activity.compose)
}
