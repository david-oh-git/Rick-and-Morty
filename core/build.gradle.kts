
import dependencies.BuildDependencies

plugins {
    id(BuildPlugins.COMMON_ANDROID_LIBRARY)
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

}