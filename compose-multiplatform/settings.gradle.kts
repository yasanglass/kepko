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
include(":foundation")
include(":component")
include(":sample")
include(":sample:composeApp")
include(":sample:androidApp")
