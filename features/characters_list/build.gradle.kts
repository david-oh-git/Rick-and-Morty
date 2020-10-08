
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
    implementation(BuildDependencies.SWIPE_REFRESH_LAYOUT)
    implementation(BuildDependencies.LIFECYCLE_EXTENSIONS)

}