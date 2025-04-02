enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        google()
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "poster-app-api-wrapper"
include(":shared")
