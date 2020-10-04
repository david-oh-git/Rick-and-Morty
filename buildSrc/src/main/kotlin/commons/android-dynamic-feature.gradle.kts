package commons

import dependencies.BuildDependencies

plugins {
    id("com.android.dynamic-feature")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlin-allopen")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

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

    androidExtensions {
        isExperimental = true
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

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation( project(BuildModules.APP))

    implementation(BuildDependencies.KOTLIN)
    implementation(BuildDependencies.MATERIAL_COMPONENTS)
    implementation(BuildDependencies.NAVIGATION_FRAGMENT)
    implementation(BuildDependencies.DYNAMIC_FEATURES_NAV)
    implementation(BuildDependencies.TIMBER)
    implementation(BuildDependencies.CONSTRAINT_LAYOUT)
    implementation(BuildDependencies.DAGGER_HILT)
    implementation(BuildDependencies.HILT_VIEWMODEL)

    kapt(BuildDependencies.HILT_VIEWMODEL_KAPT)
    kapt(BuildDependencies.DAGGER_HILT_KAPT)
}