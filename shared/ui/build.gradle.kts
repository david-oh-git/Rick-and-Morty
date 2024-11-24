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
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "io.audioshinigami.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures.dataBinding = true

    packaging {
        resources {
            excludes += setOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0"
            )
        }
    }

}

dependencies {

    implementation(libs.kotlin.reflect)
    implementation(libs.timber)
    implementation(libs.coil)
    implementation(libs.recyclerview)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.appcompat)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.material)

    kapt(libs.lifecycle.compiler)

    // Tests

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.7.0")
    testImplementation("com.google.truth:truth:1.1")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("io.mockk:mockk:1.10.2")
    testImplementation("org.robolectric:robolectric:4.4")
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("androidx.test:runner:1.3.0")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

    androidTestImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    androidTestImplementation("org.junit.platform:junit-platform-launcher:1.7.0")
    androidTestImplementation("com.google.truth:truth:1.1")
    androidTestImplementation("io.mockk:mockk:1.10.2")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test:rules:1.3.0")

    androidTestRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}