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
    compileSdkVersion(31)
    buildToolsVersion("30.0.2")

    defaultConfig {
        minSdkVersion(21)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

//    buildFeatures.dataBinding = true

    android.dataBinding.isEnabled = true
}

dependencies {

    implementation( project(":app"))
    implementation( project(":features:characters_list"))
    implementation( project(":features:favourites_list"))

    implementation("androidx.navigation:navigation-ui-ktx:2.3.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02")
    implementation("com.google.dagger:hilt-android:2.41")
    implementation("androidx.fragment:fragment-ktx:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

//    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha02")
    kapt("com.google.dagger:hilt-android-compiler:2.41")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.7.0")
    testImplementation("com.google.truth:truth:1.1")
    testImplementation("org.mockito:mockito-core:2.2.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("io.mockk:mockk:1.10.2")


    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

}