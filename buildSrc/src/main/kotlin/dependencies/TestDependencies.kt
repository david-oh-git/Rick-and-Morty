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

object TestDependencies {

    const val OBJENESIS = "org.objenesis:objenesis:${BuildVersions.OBJENESIS}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${BuildVersions.ROBOLECTRIC}"
    const val ANDROIDX_JUNIT_RUNNER = "androidx.test:runner:${BuildVersions.ANDROIDX_RUNNER}"
    const val ANDROIDX_JUNIT_RULES = "androidx.test:rules:${BuildVersions.ANDROIDX_RUNNER}"
    const val JUNIT = "junit:junit:${BuildVersions.JUNIT}"
    const val JUNIT_VINTAGE = "org.junit.vintage:junit-vintage-engine:${BuildVersions.JUNIT5}"
    const val JUNIT5_API = "org.junit.jupiter:junit-jupiter-api:${BuildVersions.JUNIT5}"
    const val JUNIT5_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${BuildVersions.JUNIT5}"
    const val JUNIT_PLATFORM = "org.junit.platform:junit-platform-launcher:${BuildVersions.JUNIT_PLATFORM}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${BuildVersions.ANDROIDX_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${BuildVersions.ESPRESSO}"
    const val TRUTH = "com.google.truth:truth:${BuildVersions.TRUTH}"
    const val MOCKITO = "org.mockito:mockito-core:${BuildVersions.MOCKITO}"
    const val HAMCREST = "org.hamcrest:hamcrest:${BuildVersions.HAMCREST}"
    const val ARCH_CORE = "androidx.arch.core:core-testing:${BuildVersions.ARCH_CORE}"
    const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildVersions.COROUTINE}"
    const val MOCKITO_ = "com.nhaarman.mockitokotlin2:mockito-kotlin:${BuildVersions.MOCKITO_}"
    const val MOCKK = "io.mockk:mockk:${BuildVersions.MOCKK}"
}