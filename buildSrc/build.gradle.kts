/*
 * MIT License
 *
 * Copyright (c) 2020 David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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