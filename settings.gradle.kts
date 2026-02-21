pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "kepko"
include(":resource")
include(":foundation")
include(":component")
include(":persistence")
include(":sample")
include(":sample:composeApp")
include(":sample:androidApp")
