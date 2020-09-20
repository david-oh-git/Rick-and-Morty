
import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
    mavenCentral()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginVersions {

    private object Versions {
        const val BUILD_TOOLS = "4.0.1"
        const val KOTLIN = "1.4.0"
        const val DAGGER_HILT = "2.28-alpha"
    }

    const val BUILD_TOOLS = "com.android.tools.build:gradle:${Versions.BUILD_TOOLS}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val KOTLIN_ALL_OPEN = "org.jetbrains.kotlin:kotlin-allopen:${Versions.KOTLIN}"
    const val DAGGER_HILT = "com.google.dagger:hilt-android-gradle-plugin:${Versions.DAGGER_HILT}"
}

dependencies {
    implementation(PluginVersions.BUILD_TOOLS)
    implementation(PluginVersions.KOTLIN_GRADLE_PLUGIN)
    implementation(PluginVersions.KOTLIN_ALL_OPEN)
    implementation(PluginVersions.DAGGER_HILT)
}