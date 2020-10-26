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

package dependencies

object BuildDependencies {

    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${BuildVersions.SWIPE_REFRESH_LAYOUT}"
    const val RECYCLE_VIEW = "androidx.recyclerview:recyclerview:${BuildVersions.RECYCLE_VIEW}"
    const val COIL = "io.coil-kt:coil:${BuildVersions.COIL}"
    const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${BuildVersions.LIFECYCLE}"
    const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildVersions.LIFECYCLE}"
    const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${BuildVersions.LIFECYCLE}"
    const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${BuildVersions.HILT_VIEWMODEL}"
    const val HILT_VIEWMODEL_KAPT = "androidx.hilt:hilt-compiler:${BuildVersions.HILT_VIEWMODEL}"
    const val DAGGER_HILT = "com.google.dagger:hilt-android:${BuildVersions.DAGGER_HILT}"
    const val DAGGER_HILT_KAPT = "com.google.dagger:hilt-android-compiler:${BuildVersions.DAGGER_HILT}"
    const val PAGING_KTX = "androidx.paging:paging-runtime-ktx:${BuildVersions.PAGING_KTX}"
    const val DYNAMIC_FEATURES_NAV = "androidx.navigation:navigation-dynamic-features-fragment:${BuildVersions.DYNAMIC_FEATURES}"
    const val DATABINDING = "androidx.databinding:databinding-adapters:${BuildVersions.DATABINDING}"
    const val PLAY_CORE = "com.google.android.play:core:${BuildVersions.PLAY_CORE}"
    const val ANDROIDX_LEGACY = "androidx.legacy:legacy-support-v4:${BuildVersions.ANDROIDX_LEGACY}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${BuildVersions.KOTLIN}"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${BuildVersions.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildVersions.APPCOMPAT}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildVersions.CORE_KTX}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildVersions.CONSTRAIN_LAYOUT}"
    const val ROOM_DB = "androidx.room:room-runtime:${BuildVersions.ROOM_DB}"
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildVersions.ROOM_DB}"
    const val ROOM_KAPT = "androidx.room:room-compiler:${BuildVersions.ROOM_DB}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${BuildVersions.NAVIGATION_FRAGMENT}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${BuildVersions.NAVIGATION_FRAGMENT}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildVersions.RETROFIT}"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${BuildVersions.RETROFIT}"
    const val HTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${BuildVersions.HTTP_LOGGING}"
    const val TIMBER = "com.jakewharton.timber:timber:${BuildVersions.TIMBER}"
    const val MOSHI = "com.squareup.moshi:moshi:${BuildVersions.MOSHI}"
    const val MOSHI_KTX = "com.squareup.moshi:moshi-kotlin:${BuildVersions.MOSHI}"
    const val MATERIAL_COMPONENTS = "com.google.android.material:material:${BuildVersions.MATERIAL_COMPONENTS}"
    const val KOTLIN_COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildVersions.KOTLIN_COROUTINES}"
    const val KOTLIN_COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildVersions.KOTLIN_COROUTINES}"

}