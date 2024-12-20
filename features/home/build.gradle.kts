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
    id("com.android.dynamic-feature")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-allopen")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "io.audioshinigami.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    android.dataBinding.enable = true

}

dependencies {

    implementation( project(":app"))
    implementation(project(":features:characters"))
    implementation( project(":features:favourites"))

    implementation(libs.navigation.ui.ktx)
    implementation(libs.appcompat)
    implementation(libs.hilt.lifecycle.viewmodel)
    implementation(libs.hilt.android)
    implementation(libs.fragment.ktx)
    implementation(libs.constraintlayout)

//    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha02")
    kapt(libs.hilt.android.compiler)


    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.platform.launcher)
    testImplementation(libs.truth)
    testImplementation(libs.mockito.core)
    testImplementation(libs.hamcrest)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.core.testing)
    testImplementation(libs.mockk)


    testRuntimeOnly(libs.junit.jupiter.engine)

}