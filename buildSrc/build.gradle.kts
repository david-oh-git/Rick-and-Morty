@file:Suppress("SpellCheckingInspection")

import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://ci.android.com/builds/submitted/6043188/androidx_snapshot/latest/repository/")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginVersions {

    private object Versions {
        const val BUILD_TOOLS = "4.0.2"
        const val KOTLIN = "1.4.0"
        const val DAGGER_HILT = "2.28-alpha"
        const val NAV_SAFE_ARGS = "2.3.0"
        const val SPOTLESS = "5.6.1"
        const val KTLINT = "0.36.0"
    }

    const val BUILD_TOOLS = "com.android.tools.build:gradle:${Versions.BUILD_TOOLS}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val KOTLIN_ALL_OPEN = "org.jetbrains.kotlin:kotlin-allopen:${Versions.KOTLIN}"
    const val DAGGER_HILT = "com.google.dagger:hilt-android-gradle-plugin:${Versions.DAGGER_HILT}"
    const val NAV_SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAV_SAFE_ARGS}"
    const val SPOTLESS = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.SPOTLESS}"
    const val KLINT = "com.pinterest:ktlint:${Versions.KTLINT}"
}

dependencies {
    implementation(PluginVersions.BUILD_TOOLS)
    implementation(PluginVersions.KOTLIN_GRADLE_PLUGIN)
    implementation(PluginVersions.KOTLIN_ALL_OPEN)
    implementation(PluginVersions.DAGGER_HILT)
    implementation(PluginVersions.NAV_SAFE_ARGS)
    implementation(PluginVersions.SPOTLESS)
    implementation(PluginVersions.KLINT)
}