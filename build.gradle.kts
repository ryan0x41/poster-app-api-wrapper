plugins {
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
}

allprojects {
    repositories {
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        google()
        mavenCentral()
    }
}

subprojects {
    tasks.withType<Test>().configureEach {
        testLogging {
            events("passed", "failed", "skipped")
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            showStandardStreams = true
        }
    }
}
