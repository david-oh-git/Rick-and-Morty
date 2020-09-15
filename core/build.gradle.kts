
import dependencies.BuildDependencies
import dependencies.TestDependencies

plugins {
    id(BuildPlugins.COMMON_ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ALL_OPEN)
    kotlin(BuildPlugins.KOTLIN_ANDROID)
    kotlin(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    kotlin(BuildPlugins.KOTLIN_KAPT)
}

allOpen{
    //So you can mock classes without opening them
    annotation("io.audioshinigami.core.annotations.OpenClass")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

}

dependencies {

    implementation(BuildDependencies.ROOM_DB)
    implementation(BuildDependencies.ROOM_KTX)
    implementation(BuildDependencies.RETROFIT)
    implementation(BuildDependencies.RETROFIT_GSON)
    implementation(BuildDependencies.HTTP_LOGGING)
    implementation(BuildDependencies.MOSHI)
    implementation(BuildDependencies.MOSHI_KTX)

    kapt(BuildDependencies.ROOM_KAPT)

    // Tests
    testImplementation(TestDependencies.JUNIT5_API)
    testImplementation(TestDependencies.JUNIT_PLATFORM)
    testImplementation(TestDependencies.TRUTH)
    testImplementation(TestDependencies.MOCKITO_)
    testImplementation(TestDependencies.HAMCREST)
    testImplementation(TestDependencies.COROUTINE_TEST)
    testImplementation(TestDependencies.ARCH_CORE)

    testRuntimeOnly(TestDependencies.JUNIT5_ENGINE)

}