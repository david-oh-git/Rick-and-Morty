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