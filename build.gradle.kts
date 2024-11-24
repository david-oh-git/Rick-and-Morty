/*
 * MIT License
 *
 * Copyright (c) 17/11/2020 16:24   David Osemwota.
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



plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
}
val kotlinVersion = "1.6.10"
buildscript {

    dependencies {
//        classpath("com.android.tools.build:gradle:7.4.2")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("org.jetbrains.kotlin:kotlin-allopen:1.9.23")
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")
        classpath("com.diffplug.spotless:spotless-plugin-gradle:5.6.1")
        classpath("com.pinterest:ktlint:0.39.0")
    }
}

allprojects {

    configurations.all {

        resolutionStrategy {
            force(
//                "org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}",
//                "org.jetbrains.kotlin:kotlin-stdlib-common:${kotlinVersion}",
//                "org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}",
//                "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion",
//                "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion",
//                "org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion",
                "org.objenesis:objenesis:3.2"
            )
        }

    }
}

