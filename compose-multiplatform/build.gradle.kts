plugins {
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.jetbrains.compose.hotreload) apply false
    alias(libs.plugins.vanniktech.maven.publish) apply false
    alias(libs.plugins.roborazzi) apply false
    alias(libs.plugins.arturbosch.detekt) apply true
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom(
        "$projectDir/detekt/detekt.yml",
        "$projectDir/detekt/detekt-kepko.yml",
    )
    autoCorrect = true
}

allprojects {
    group = "glass.yasan.kepko"
    version = "1.2.5"
}

fun Project.configureDetekt() {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        buildUponDefaultConfig = true
        config.setFrom(
            "$rootDir/detekt/detekt.yml",
            "$rootDir/detekt/detekt-kepko.yml",
        )
        source.from(
            "src/androidMain/kotlin",
            "src/androidTest/kotlin",
            "src/commonMain/kotlin",
            "src/commonTest/kotlin",
            "src/iosMain/kotlin",
            "src/iosTest/kotlin",
            "src/jvmAndroidMain/kotlin",
            "src/jvmMain/kotlin",
            "src/jvmTest/kotlin"
        )
    }
}

fun Project.configurePublishing() {
    apply(plugin = "com.vanniktech.maven.publish")
    configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
        publishToMavenCentral(
            automaticRelease = true,
            validateDeployment = false,
        )

        signAllPublications()

        pom {
            name.set("Kepko")
            description.set("A work-in-progress design system for Compose Multiplatform.")
            inceptionYear.set("2025")
            url.set("https://github.com/yasanglass/kepko/")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set("yasanglass")
                    name.set("Yasan Glass")
                    url.set("https://github.com/yasanglass/")
                }
            }
            scm {
                url.set("https://github.com/yasanglass/kepko/")
                connection.set("scm:git:git://github.com/yasanglass/kepko.git")
                developerConnection.set("scm:git:ssh://git@github.com/yasanglass/kepko.git")
            }
        }
    }
}

subprojects {
    val isSample = path.contains("sample")

    configureDetekt()

    if (isSample.not()) {
        configurePublishing()
    }
}
