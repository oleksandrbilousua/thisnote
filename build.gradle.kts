// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id ("com.google.dagger.hilt.android") version "2.50" apply false
    id("io.gitlab.arturbosch.detekt") version "1.20.0"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}
buildscript {
    dependencies {
        // other plugins...
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}