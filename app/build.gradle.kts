/*
 * MIT License
 *
 * Copyright (c) 17/11/2020 16:23   David Osemwota.
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
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "io.audioshinigami.projectm"
    compileSdkVersion(31)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "io.audioshinigami.projectm"
        minSdkVersion(21)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        getByName("release"){
            isMinifyEnabled = true
            proguardFiles( getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("debug"){
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            versionNameSuffix = "-debug"
            isTestCoverageEnabled = true
        }

    }

    android.dataBinding.isEnabled = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    dynamicFeatures = mutableSetOf(
        ":features:home",
        ":features:characters_list",
        ":features:favourites_list"
    )

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    signingConfigs {

        getByName("debug") {
            keyAlias = "android"
            keyPassword = "android"
            storeFile = file("rickandmorty_debug.jks")
            storePassword = "android"

            isV2SigningEnabled = true
        }
    }

//    packagingOptions {
//        exclude("META-INF/LICENSE.md")
//    }
}

dependencies {
    implementation( fileTree( mapOf( "dir" to "libs", "include" to  listOf("*.jar")  )))

    implementation( project(":core"))
    api( project(":shared:ui"))

    val kotlin = "1.6.10"
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.1")
    implementation("com.google.android.play:core:1.8.0")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.3.1")
    implementation("com.google.android.material:material:1.2.1")
    implementation("com.google.dagger:hilt-android:2.41")
    implementation("com.jakewharton.timber:timber:4.7.1")
    
    kapt("com.google.dagger:hilt-android-compiler:2.41")
}
