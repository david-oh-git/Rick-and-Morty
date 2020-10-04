
import dependencies.BuildDependencies
import dependencies.TestDependencies

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.DAGGER_HILT_PLUGIN)
    kotlin(BuildPlugins.KOTLIN_ANDROID)
    kotlin(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    kotlin(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {

        getByName(BuildType.RELEASE){
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles( getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName(BuildType.DEBUG){
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }

    }

    buildFeatures {
        dataBinding = true
    }

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
        BuildModules.Features.HOME,
        BuildModules.Features.CHARACTERS_LIST
    )

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }
}

dependencies {
    implementation( fileTree( mapOf( "dir" to "libs", "include" to  listOf("*.jar")  )))

    implementation( project(BuildModules.CORE))
    api( project(BuildModules.Features.UI))

    implementation(BuildDependencies.KOTLIN)
    implementation(BuildDependencies.KOTLIN_REFLECT)
    implementation(BuildDependencies.CORE_KTX)
    implementation(BuildDependencies.APPCOMPAT)
    implementation(BuildDependencies.CONSTRAINT_LAYOUT)
    implementation(BuildDependencies.NAVIGATION_FRAGMENT)
    implementation(BuildDependencies.PLAY_CORE)
    implementation(BuildDependencies.DYNAMIC_FEATURES_NAV)
    implementation(BuildDependencies.MATERIAL_COMPONENTS)
    implementation(BuildDependencies.DAGGER_HILT)
    implementation(BuildDependencies.TIMBER)
    
    kapt(BuildDependencies.DAGGER_HILT_KAPT)

    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO)

}
