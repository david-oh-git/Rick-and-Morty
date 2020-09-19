
import dependencies.BuildDependencies
import dependencies.TestDependencies

plugins {
    id(BuildPlugins.ANDROID_DYNAMIC_FEATURE)
    id("kotlin-android")
    id(BuildPlugins.DAGGER_HILT_PLUGIN)
    kotlin(BuildPlugins.KOTLIN_ANDROID)
    kotlin(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    kotlin(BuildPlugins.KOTLIN_KAPT)

}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures.viewBinding = true
    buildFeatures.dataBinding = true

}

dependencies {

    implementation( project(BuildModules.APP))
    implementation( project(BuildModules.Features.UI))
    implementation( project(BuildModules.CORE))


    implementation(BuildDependencies.KOTLIN)
    implementation(BuildDependencies.KOTLIN_COROUTINES_ANDROID)
    implementation(BuildDependencies.KOTLIN_COROUTINES_CORE)
    implementation(BuildDependencies.NAVIGATION_FRAGMENT)
    implementation(BuildDependencies.CONSTRAINT_LAYOUT)
    implementation(BuildDependencies.MATERIAL_COMPONENTS)
    implementation(BuildDependencies.PAGING_KTX)
    implementation(BuildDependencies.TIMBER)
    implementation(BuildDependencies.DAGGER_HILT)
    implementation(BuildDependencies.NAVIGATION_FRAGMENT)
    implementation(BuildDependencies.LIFECYCLE_EXTENSIONS)
    implementation(BuildDependencies.VIEWMODEL)

    kapt(BuildDependencies.DATABINDING)
    kapt(BuildDependencies.DAGGER_HILT_KAPT)

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

    testRuntimeOnly(TestDependencies.JUNIT5_ENGINE)
}