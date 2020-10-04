
import dependencies.BuildDependencies
import dependencies.TestDependencies

plugins {
    id("commons.android-dynamic-feature")
}

android {
    buildFeatures.dataBinding = true
}

dependencies {

    implementation( project(BuildModules.CORE))

    implementation(BuildDependencies.KOTLIN_COROUTINES_ANDROID)
    implementation(BuildDependencies.KOTLIN_COROUTINES_CORE)
    implementation(BuildDependencies.PAGING_KTX)
    implementation(BuildDependencies.LIFECYCLE_EXTENSIONS)
    implementation(BuildDependencies.VIEWMODEL)

    // Tests
    testImplementation( project(BuildModules.TEST_UTILS))

    testImplementation(TestDependencies.JUNIT5_API)
    testImplementation(TestDependencies.JUNIT_PLATFORM)
    testImplementation(TestDependencies.TRUTH)
    testImplementation(TestDependencies.MOCKITO_)
    testImplementation(TestDependencies.HAMCREST)
    testImplementation(TestDependencies.COROUTINE_TEST)
    testImplementation(TestDependencies.ARCH_CORE)
    testImplementation(TestDependencies.MOCKK)
    implementation(BuildDependencies.SWIPE_REFRESH_LAYOUT)
    implementation(BuildDependencies.LIFECYCLE_EXTENSIONS)

    testRuntimeOnly(TestDependencies.JUNIT5_ENGINE)
}