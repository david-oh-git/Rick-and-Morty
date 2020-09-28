//apply plugin: "com.android.dynamic-feature"
//apply plugin: 'kotlin-android'
//apply plugin: 'kotlin-android-extensions'

import dependencies.BuildDependencies

plugins {
    id(BuildPlugins.ANDROID_DYNAMIC_FEATURE)
    id("kotlin-android")
    kotlin(BuildPlugins.KOTLIN_ANDROID)
    kotlin(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    kotlin(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT_PLUGIN)
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

    buildFeatures.dataBinding = true

}

dependencies {

    implementation( project(BuildModules.APP))
    implementation( project(BuildModules.Features.CHARACTERS_LIST))

    implementation(BuildDependencies.NAVIGATION_FRAGMENT)
    implementation(BuildDependencies.NAVIGATION_UI)
    implementation(BuildDependencies.CONSTRAINT_LAYOUT)
    implementation(BuildDependencies.MATERIAL_COMPONENTS)
    implementation(BuildDependencies.APPCOMPAT)
    implementation(BuildDependencies.DAGGER_HILT)
    implementation(BuildDependencies.HILT_VIEWMODEL)

    kapt(BuildDependencies.HILT_VIEWMODEL_KAPT)
    kapt(BuildDependencies.DAGGER_HILT_KAPT)


}